# JANCALA

---

## Table of Contents
- [Purpose](#purpose)
- [Installation Instructions](#installation-instructions)
- [Usage Guide](#usage-guide)
- [Feedback](#feedback)
- [Contributors](#contributors)

---

## Purpose
The purpose of this project is to modernize the classic Mancala game by integrating engaging features, such as:
- AI opponents with adjustable difficulty levels.
- A dynamic leaderboard for players to track and compare scores.
- SMS notifications to keep players updated on their leaderboard position and gameplay activity.

Additionally, this project was designed with the following objectives:
1. **User Experience**:
    - Provide a fun and intuitive gaming experience for Mancala fans.
    - Create a competitive environment through a real-time leaderboard.
2. **Educational Goals**:
    - Demonstrate clean architecture principles (e.g., separation of concerns, SOLID design).
    - Gain hands-on experience with third-party API integration.

---

## Installation Instructions

To utilize the leaderboard feature, follow these steps:

//TO DO: Ivan's leaderboard API



To utilize SMS notifications, follow these steps:

1. **Sign up for Twilio**:
    - Visit the [Twilio signup page](https://login.twilio.com/u/signup?state=hKFo2SBqVGtXWkJ3WktFLWlTT3RjRTdDc2ozWDByXy1ES3B2cKFur3VuaXZlcnNhbC1sb2dpbqN0aWTZIDNTcWdTSjlHWUlDb0VPWnN6SmY3NVpzQndMNV9NaGI2o2NpZNkgTW05M1lTTDVSclpmNzdobUlKZFI3QktZYjZPOXV1cks).
    - Be sure to enter the phone number you would like your notifications to be sent to.
    - Complete the verification process to access your `Account SID`, `Auth Token`, and `Twilio Phone Number`.
   
    Example Screenshot:
    
    <img src="images_used_for_readme/img_2.png" alt="twilio example" width="500"/>

2. **Set up Twilio credentials**:
    - Navigate to the following file path:
      ```plaintext
      src -> main -> java -> us.jonathans -> app -> AppBuilder
      ```
    - Enter your Twilio credentials in the appropriate fields.

   Example screenshot:

   <img src="images_used_for_readme/img_1.png" alt="enter information" width="600"/>

---

## Usage Guide

1. **Launch the Program**:
    - After installation, run the program to open the Mancala game interface.

2. **Enter Details**:
    - Provide your username.
    - Optionally, enable **SMS notifications** and enter your phone number.

3. **Select AI Difficulty**:
    - Choose the engine difficulty level (`minimax_easy`, `minimax_medium`, or `minimax_hard`).

4. **Start Playing**:
    - Press the **Start** button to initialize the game. Stones will appear on the Mancala board.

5. **Cancel or Stop**:
    - Use the **Cancel** button if you need to stop or reset.

6. **Leaderboard and Notifications**:
    - After each game, your score is submitted to the leaderboard.
    - If SMS notifications are enabled, you will receive updates about your rank.

Example interface screenshot:

<img src="images_used_for_readme/img.png" alt="interface" width="600"/>

---

## Feedback
//Mina will do this part

Take the survey here: [Jancala Survey](https://docs.google.com/forms/d/e/1FAIpQLSerZEJWytn4krOZ3ecCrA8d6ycYwIQJCVlRL-O9_T4XPuo3kA/viewform).

---

## Contributors
- **Ivan Chepelev**: [GitHub](https://github.com/ch-iv)
- **Fiza Kabeer**: [GitHub](https://github.com/fizakabeer)
- **Richard Lin**: [GitHub](https://github.com/richardrLin)
- **Jonathan Qiao**: [GitHub](https://github.com/jonathanqiao1)
- **Mina Wu**: [GitHub](https://github.com/MW0808)

---

