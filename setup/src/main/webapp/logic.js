




var userid;
var movieid;
var username;


//--------Enable Tab -------------

function enable() {
    document.getElementById("feed-tab").removeAttribute('disabled');
    document.getElementById("profile-tab").removeAttribute('disabled');
    document.getElementById("my-movies-tab").removeAttribute('disabled');

    const feedTab = new bootstrap.Tab(document.getElementById("feed-tab"));
    feedTab.show();
}

// Fetch all movies

function allMovies() {
    fetch("http://localhost:8080/postbook/webapi/project/movies/allmovies", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(resp => resp.json())
        .then(data => {
            mapMovies(data);
            console.log(data);
        });
}

// Map movies to HTML
function mapMovies(movies) {
    let listString = '<div class="container"><div class="row">';
    for (let i = 0; i < movies.length; i++) {

        listString += `
        <div class="col-md-4 mb-4">
        <style>
        .card:hover {
            transform: scale(1.05);
            transition: transform 0.3s ease;
        }
    </style>
    <div class="card h-100 shadow-lg rounded" style="width: 20rem;">
        
        <video src="${movies[i].imageUrl}" class="card-img-top" style="height: 250px; object-fit: cover;" autoplay loop muted></video>
        <div class="card-body">
            <h5 class="card-title">${movies[i].movieTitle}</h5>
            <p class="card-text" style="font-size: 14px; height: 100px; overflow: hidden;">${movies[i].movieDescription}</p>
            <p class="card-text" style="font-size: 14px;"><strong>Director:</strong> ${movies[i].movieDirector}</p>
            <p class="card-text" style="font-size: 14px;"><strong>Genre:</strong> ${movies[i].movieGenere}</p>
            <button type="button" class="btn btn-primary" onclick="modalClickHandle(${movies[i].moviesId})" style="font-size: 14px;" >Add Review</button>
        </div>
    </div>
</div>`;
    }
    listString += '</div></div>';
    document.getElementById('movieList').innerHTML = listString;
}

// Submit Review function
function submitReview() {
    // Your code to handle review submission
    // This function should handle the logic when the "Add" button in the modal is clicked
}

// --------------- User Sign Up ------------------



function signUp() {
    console.log("function work");

    const users = {
        userName: document.getElementById("username").value,
        userEmail: document.getElementById("useremail").value,
        userPassword: document.getElementById("userpassword").value,
    };

    document.getElementById("username").value = "";
    document.getElementById("useremail").value = "";
    document.getElementById("userpassword").value = "";


    fetch("http://localhost:8080/postbook/webapi/project/movieusers/add", {
        method: 'POST',

        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(users)


    }).then((res) => {
        res.json()
    }).then((data) => {
        console.log(data);
        alert("signup sucess");
        enable();
            document.getElementById("feed-tab").disabled = false;
            document.getElementById("profile-tab").disabled = false;
            document.getElementById("my-movies-tab").disabled = false;
            document.getElementById("sign-in-tab").hidden = true;
            document.getElementById("sign-up-tab").hidden = true;
            document.getElementById("logout-tab").hidden = false;
    })

}


//-----------------User Sign In--------------------
function signIn() {
    const userLogin = {
        userEmail: document.getElementById("useremaillogin").value,
        userPassword: document.getElementById("userpasswordlogin").value,
    };

    document.getElementById("useremaillogin").value = "";
    document.getElementById("userpasswordlogin").value = "";


    fetch("http://localhost:8080/postbook/webapi/project/movieusers/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userLogin)
    }).then((res) => res.json())
        .then((data) => {
            console.log(data);
            userid = data.userId;
            username = data.userName;
            console.log(userid);
            alert("Login Sucess...");
            enable();
            document.getElementById("feed-tab").disabled = false;
            document.getElementById("profile-tab").disabled = false;
            document.getElementById("my-movies-tab").disabled = false;
            document.getElementById("sign-in-tab").hidden = true;
            document.getElementById("sign-up-tab").hidden = true;
            document.getElementById("logout-tab").hidden = false;
        }).catch((err) => {
            console.log("Error:" + err);
        });

}




//---------------Modal --------------------
function modalClickHandle(moviesid) {
    movieid = moviesid;
    console.log("inside Function ")
    jQuery.noConflict();
    jQuery('#addReviewModal').modal('show')
}


function btnClose() {
    jQuery('#addReviewModal').modal('close')
}

//--------------- Add New Reviews -----------------


function addNewReview() {


    const reviewData = {
        reviewText: document.getElementById('reviewText').value,

        movies: {
            moviesId: movieid,
        },
        movieusers: {
            userId: userid,
            userName: username

        }

    }

    console.log(reviewData);

    fetch("http://localhost:8080/postbook/webapi/project/reviews/add", {
        method: 'POST',

        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reviewData)
    }).then((res) => {
        res.json();
    }).then(data => {

        console.log(data);
        alert("Review Added");
    });

}



//------------All Reviews---------------


function viewAllReview() {
    fetch("http://localhost:8080/postbook/webapi/project/reviews/getallreviews", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(resp => resp.json())
        .then(data => {
            mapReviews(data);
            console.log(data);
            console.log(data.reviewId)
        })
        .catch(error => console.error('Error fetching reviews:', error));
}


function mapReviews(reviews) {
    var listString = "";
    reviews.reviewId;
    for (let i = 0; i < reviews.length; i++) {
        listString += `<div class="container mt-5">
        <div class="card border-dark">
            <div class="card-header  text-black">
                <h5 class="card-title">${reviews[i].movies.movieTitle}</h5>
            </div>
            <div class="card-body">
                <p class="card-text">${reviews[i].reviewText}</p>
                <p class="card-text">
                    <small class="text-muted">Review by: ${reviews[i].username}</small>
                </p>
                <div class="d-flex justify-content-between align-items-center">
                    <!-- Like button -->
                    <button type="button" class="btn btn-light like-button">
                        <i class="bi bi-heart like-heart text-danger"></i>
                    </button>
                    <!-- Comment icon -->
                    <i class="bi bi-chat"></i>
                </div>
            </div>
        </div>
    </div>
    `
    }

    document.getElementById("reviewList").innerHTML = listString;
}

function viewMyReviews() {
    console.log("My Reviews");
    fetch(`http://localhost:8080/postbook/webapi/project/reviews/getmyreviews/${userid}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(resp => resp.json())
        .then(data => {
            myReviewMap(data);
            console.log(data);
            console.log(data.reviewId)
        })
        .catch(err => console.log(err))
}

function myReviewMap(review) {
    var listString = "";

    for (let i = 0; i < review.length; i++) {
        listString += `
				<div class="container mt-5">
					<div class="card">
						
						<div class="card-body">
							<h5 class="card-title">${review[i].movies.movieTitle}</h5>
							<p class="card-text">${review[i].reviewText}</p>
							<p class="card-text">
								<small class="text-muted">Review by: ${review[i].username}</small>
							</p>
							<!-- Heart-shaped like button -->
							<button onClick="deleteReview(${review[i].reviewId})"  type="button" class="btn btn-link like-button">
								<i class="bi bi-trash"></i>
								
                    			
							</button>
							<i class="bi bi-chat"></i>
						</div>
					</div>
				</div>
		
		`
    }

    document.getElementById("myreviewList").innerHTML = listString;
}

function deleteReview(reviewid) {
    console.log(reviewid);
    fetch(`http://localhost:8080/postbook/webapi/project/reviews/deletemyreviews/${reviewid}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((resp) => {
        //console.log(resp)
        //myReviewMap();
        viewMyReviews();
        resp.json()
    }).then((data) => {
        console.log(data)
    })
}


//---------Logout Function----------


function logout() {
    document.getElementById("feed-tab").disabled = true;
    document.getElementById("profile-tab").disabled = true;
    document.getElementById("my-movies-tab").disabled = true;
    document.getElementById("sign-in-tab").hidden = false;
    document.getElementById("sign-up-tab").hidden = false;
    document.getElementById("logout-tab").hidden = true;

    const signInTabButton = document.getElementById("sign-in-tab");
    signInTabButton.click();
} 
