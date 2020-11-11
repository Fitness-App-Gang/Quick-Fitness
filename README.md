# Quick-Fitness
## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Gives users information about muscle groups and how to work them out. Allows them to make work out routines and share with other users as well.

### App Evaluation
- **Category:** Fitness / Social Networking
- **Mobile:** This app would be primarily developed for mobile but could be viable on pcs. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Users could post their tips and full workouts to get others motivated and into the spirit of fitness.
- **Market:** Anyone who wants to work out and share their experiences.
- **Habit:** This app could be used as often or unoften as the user wants. We think that their fitness should be their choice and we want to make it easy and accesable for them to get into.
- **Scope:** First we would start with giving them information on workouts, letting them share their routines. Then we would want more user interactions so letting people make fitness groups where they can track their progress and compare and share within the group.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User logs in to access social features and routines.
* User picks what body part they want to work out, and gets a list of workouts, and videos to follow.
* Users can can view and post routines and progress.
* Profile pages for each user
* Settings (Accesibility, Notification, General, etc.)

**Optional Nice-to-have Stories**

* Let user set custom sets and reps
* Chat functionality between users
* Allow users to add workouts to body parts
* Allow users to track progress
* Allow users to track and modify sets and reps

### 2. Screen Archetypes

* Login 
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information and interact with others 
* Messaging Screen - Chat for users to communicate (direct 1-on-1)
   * Let users communicate and share their workout plans and fun exercises
* Profile Screen 
   * Allows user to view their posts, saved routines, upload a photo and fill in information that is interesting to them and others.
* Muscle Selection Screen.
   * Allows user to select the muscle group that they would like to work out. Allo be able to choose their desired muscle, artist, genre of preference and begin listening and interacting with others..
* Wourkout Information Screen
    * Gives info on the muscle group and lists excercises to do to build that muscle up. Also gives video references to help you follow along.
    * record your sets and reps for each session

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Muscle selection
* Feed
* Share workouts
* Profile

Optional:
* Let user comment on post
* Let user modify sets and reps

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available.
* Muscle Selection -> Jumps to excercise list/videos.
* Feed -> view details of post.
* Share workouts -> Create workout plan and post to feed.
* Profile -> Text field to be modified.
* Profile -> Edit past posts.

## Schema
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| image author |
   | image         | File     | image that user posts |
   | caption       | String   | image caption by author |
   | commentsCount | Number   | number of comments that has been posted to an image |
   | likesCount    | Number   | number of likes for the post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all posts where user is author
         ```swift
         let query = PFQuery(className:"Post")
         query.whereKey("author", equalTo: currentUser)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error {
               print(error.localizedDescription)
            } else if let posts = posts {
               print("Successfully retrieved \(posts.count) posts.")
           // TODO: Do something with posts...
            }
         }
         ```
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Create Post Screen
      - (Create/POST) Create a new post object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image
#### [OPTIONAL:] Existing API Endpoints
##### An API Of Ice And Fire
- Base URL - [http://www.anapioficeandfire.com/api](http://www.anapioficeandfire.com/api)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /characters | get all characters
    `GET`    | /characters/?name=name | return specific character by name
    `GET`    | /houses   | get all houses
    `GET`    | /houses/?name=name | return specific house by name

##### Game of Thrones API
- Base URL - [https://api.got.show/api](https://api.got.show/api)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /cities | gets all cities
    `GET`    | /cities/byId/:id | gets specific city by :id
    `GET`    | /continents | gets all continents
    `GET`    | /continents/byId/:id | gets specific continent by :id
    `GET`    | /regions | gets all regions
    `GET`    | /regions/byId/:id | gets specific region by :id
    `GET`    | /characters/paths/:name | gets a character's path with a given name
    
    
    
    

## Basic Wireframe
<img src='BasicWireframe.png' title='Video Walkthrough' width='' alt='Video Walkthrough' />

### Top Ideas
#Fitness Tracker - with each body part, and it gives you a list of excercises to do and videos
#Body figure with clickable muscles that take you to the muscle explinations, and excercises
#easy medium and hard levels for exercises and a custom sets and reps option
#fragments for videos, and another for sets and reps, another for a social aspects (sharing workouts and such)
* videos/home fragment features:
    * body part ui/main menu
    * top five exercises for that muscle
    * videos for each of those exercises
* log fragment features:
    * local or account based storage
    * record your sets and reps for each session
* social fragment features:
    * sharing workouts and routines
    * feed with parse


### Other Ideas
#News app, and it gives local news based on on your area, watchlist feature
#Car app, enter model year, and gives the look of the car
#Instagram
#Craigslist(but for one thing)
#Recipe App- grocery List, some premade recipes, users can create their own, filter by the type of food
#Monster Hunter Set Builder - lets users build sets based on what skills they want

Fitness-- iii
news--
car--
insta--
Craigs--
Recipe--ii
Monst Hunt--ii
