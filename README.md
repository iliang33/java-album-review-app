# Album Review Application 

## What it does  

This is an application that allows one to organize and track their thoughts on **albums** they have listened to.  

## Who will use it 

Anyone that wishes to have a place to keep track of their album reviews in an easy and organized way.  

## Why it's of interest to me  

This project is of interest to me as over the summer I started fully listening to albums front to back when
before I would only pick and choose popular songs from the albums to listen to. While listening, 
I would do a review for it by scoring each track out of ten and giving an overall score at the end. 
Because of this I thought it would be enjoyable to turn this into a project.

## User Stories  
  
- As a user, I want to be able to create and remove album reviews  
- As a user, I want to be able to add and remove album reviews to different categories to group them  
- As a user, I want to be able to see all of my reviews 
- As a user, I want to be able to sort reviews by critera such as by artist name alphabetical  
- As a user, I want to be able to update existing reviews and categories   
- As a user, I want to be able to merge the tracklists of two albums together  
- As a user, I want to be given the option to save all my reviews and categories 
- As a user, I want to be given the option to load all of my saved reviews and categories  

# Instructions for End User

- You can view the panel that displays the albums that have already been added to categories by clicking the View tab at the top,
and then clicking the button that corresponds to the wanted category. If you don't see it, try pressing the refresh button.
- You can sort albums by certain criteria by clicking the corresponding button in the Albums tab, then going to the View tab and
clicking the refresh button
- You can add albums to categories by going to the Categories tab and clicking the add to category button
- You can locate my visual component by clicking the Stats tab
- You can save the state of my application by clicking the save button in the Home tab
- You can reload the state of my application by clicking the load button in the Home tab

# Phase 4: Task 2

NOTE: I did not add logging for displaying all X's in my Y as all of this is done in the ui package
and nothing changes in my model package when the X's are displayed so I was unsure where to 
call logEvent.

Sat Nov 22 11:37:46 PST 2025
Album created

Sat Nov 22 11:37:46 PST 2025
Album added to all albums list

Sat Nov 22 11:38:12 PST 2025
Song created

Sat Nov 22 11:38:12 PST 2025
Song added to tracklist

Sat Nov 22 11:38:21 PST 2025
Song created

Sat Nov 22 11:38:21 PST 2025
Song added to tracklist

Sat Nov 22 11:38:28 PST 2025
Song created

Sat Nov 22 11:38:28 PST 2025
Song added to tracklist

Sat Nov 22 11:38:40 PST 2025
Song removed from tracklist

Sat Nov 22 11:38:53 PST 2025
Album created

Sat Nov 22 11:38:53 PST 2025
Album added to all albums list

Sat Nov 22 11:39:09 PST 2025
Album created

Sat Nov 22 11:39:09 PST 2025
Album added to all albums list

Sat Nov 22 11:39:13 PST 2025
Albums sorted by artist alphabetical

Sat Nov 22 11:39:14 PST 2025
Albums sorted by name alphabetical

Sat Nov 22 11:39:16 PST 2025
Albums sorted by rating high to low

Sat Nov 22 11:39:32 PST 2025
Album category created

Sat Nov 22 11:39:32 PST 2025
Album category added to all categories list

Sat Nov 22 11:39:37 PST 2025
Album category created

Sat Nov 22 11:39:37 PST 2025
Album category added to all categories list

Sat Nov 22 11:39:45 PST 2025
Album added to album category

Sat Nov 22 11:40:04 PST 2025
Album removed from album category

Sat Nov 22 11:40:18 PST 2025
Album category removed from all categories list

Sat Nov 22 11:40:33 PST 2025
Album removed from all albums list

# Phase 4: Task 3

One thing I would do is create a new abstract class and have Album, AlbumCategory, and Song extend it.
This is because the three classes have a lot of the same behaviour and there is duplicate code as well.
All three classes add and remove objects from a list and share some of the exact same getters. One other
thing I would do is create new classes and have them handle some of the responsiblities of the ReviewManager.
This is because I feel the ReviewManager is doing too much for one class as it adds/removes from albums,
categories, and tracklists, as well as sorts albums by different categories and contains many helper
methods concerning finding wanted songs, albums, and categories.





 