# simple-chat
ASAPP Fullstack Challenge
=========================

Welcome to your challenge project!

For this challenge, we ask that you implement a solution at home in your own time. Please send us your results - including code and setup instructions - within a week. If you need more time, let us know.


The Details
-----------

Your challenge is to design and implement a basic chat service that allows users to broadcast messages to each other in a shared chat room. It should include a server backed by a persistent data store and a web client.

Your server should support the following requests:

- Login User
Takes a username and, if the user doesn’t already exist it, saves it to the data store.

- Send Message
Takes a message and saves that to the data store. 

- Fetch Messages
Loads all messages. The server should also have the capability of loading only "new" messages, i.e. messages that a user hasn't seen yet. It's up to you how to support this.

Your client should support the following use cases:

- Login
Let’s a user login.

- Basic chat
After logging in, a user can send messages to the shared chat room. When a message is sent, it should show up in the windows of all the users who are logged in. Upon logging in, a user should also be able to see the older messages that were sent previously.

Suggestions
-----------

- Think about both the technical design (how to model your data, structure your APIs) and also the user experience (how should a chat product work).
- At ASAPP, we use a lot of ReactJS and Go. For the challenge, we’d like you to be able to work in languages with which you’re comfortable, but we do suggest the following:
    Frontend: Javascript (including React, Angular, etc)
    Backend: Go, Java, Python
    Database: SQL (including SQLite, MySQL, Postgres)
- Use open source libraries rather than reinventing the wheel. Here are a couple of relevant tools that we use:
    github.com/facebook/react
    github.com/go-sql-driver/mysql
- Have fun!

Follow-up questions
-------------------
We’ll discuss these as part of the project review. Don't worry if you don't have all the answers off the top of your head. We’re very much looking for your ability to reason about and work through these kinds of questions.

- How would you implement picture messages? Where is image data stored? How/when are thumbs generated? How are URLs decided on?

- How well does your project scale? What if the number of users grow to 1000? To 1000000? (And the conversation history grows too.)

- How will you scale your server beyond a single machine? Keep in mind that different users may be connected to different machines.
