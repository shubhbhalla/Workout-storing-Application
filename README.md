
## Workout Storing Application

This application is for people who go to the gym and do **weight lifting**.
When the user uses the program for the first time, they have to enter their name, age, weight, sex, height and 
intensity of their workouts. The application determines the user's *total daily energy expenditure* (TDEE). 
The user can choose to eat more than their TDEE if they want to gain weight, or they can eat less than their 
TDEE if they want to lose weight.

The application will do the following things (this list is not exhaustive):
- calculate the TDEE of the user
- choose the weight lifting exercises the user performed from the predefined exercises list
- choose the sets and repetitions of the exercise that they performed
- stores the weights used in each set of the exercise
- store the duration of the weightlifting session
- prints the workout record of the user

The user should enter the duration of their weight lifting session, weights 
used (including sets and reps) in weight lifting session in the application after completing their workout. 

This project is very close to me, and I have been dreaming to make a project like this since
the beginning of University. Before coming to UBC, I started to do weight training and a bit
of cardio along with it. I always wanted a workout app to log my weight lifting sessions at the gym,
albeit all the application that I found were paid and did not offer all the features that i desperately needed.
I decided that after learning to code, I would make an application which I use in my everyday life.
My brother is a fitness freak just like me, and he has been into weight lifting for the past 3 years.
I wanted to make this app for myself, my brother, and my friends who are health conscious and adrenaline junkies
just like me. One of the main stimulus for muscle growth is *progressively overloading* your muscles in each exercise,
and to ensure that the user progressively overloads their muscles, the application logs the weights used
by the user for the exercises so that they can visualise their progress.

## User Stories

- As a user, I want the application to calculate my **TDEE** when I log in for the first time.
- As a user, I want to define custom sets and reps (with weights used) for a personalised exercise routine.
- As a user, I want to add different Exercises to my Workouts.
- As a user, I want to add new Workouts to my Workout Records.
- As a user, I want the application to show all my logged workouts.
- As a user, I want the application to save my previously added workouts when I terminate the application.
- As a user, I want the application to display my previously stored workouts when I open the application again.
- Phase 4: Task 2, made a new exception class called NegativeInputException to check if the input entered is negative, 
used in class Person in methods - changeHeight, changeWeight and changeAge. Tested this exception class in tests for 
Person.
- Phase 4: Task 3, Refactoring - 
    - I would make the association from people to person a bi directional association, so that whenever I want 
     to create a new person I can easily look into the people object to see my list of person and find that if any
     name matches the current name that I’m trying to create then the new person cannot be created.
     - I would make more exception classes, for example name entered is null or workout duration can't be a string,
      so that different cases of inputs can be covered without the program crashing.
