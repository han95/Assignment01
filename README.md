# Tracker
Android App to record expense items in travel.

(A UML diagram is in doc folder, and a two minutes video is in video folder.)

User will see a list of travel claim when the application starts, showing the description(name) of the claim as well as its state.

Clicking the menu called "add new claim" will leads user to another page, which allows user to edit a new claim. When user saves claim, the state will automaically changes to "Submitted". Then the application will jumps back to main page.

Clicking a submitted claim allows user to read saved information. Clicking "Return" button changes the state to "Returned" and allow user do further ediion. When user finish editing it, user can click "submit travel claim" button again.

When user opens a submitted claim, approving claim is also allowed when button called "approve" is clicked. Application will considers an approved claim as a final version, without allowing user to change it any more.

Clicking "Email this claim" button will start a new page asking user to input email address. if the address is in wrong format, the application will warning user.

When user long click a saved claim application, the application will show an alert dialogue to ask the user whether he/she wants to delete the whole claim. Clicking "cancle" will cancle this activity, and clicking "delete" will delete claim.
