# Android Stori Technical Test
A banking application must be made that allows user registration, user login, bank
information: balance, list of movements made and detail of the movement.

## :scroll: Problem to solve

The problems to solve are the following:
The first view is the one corresponding to the login of the application, and contains a
text field for the username, a text field for the password, a login button and a register
button.The challenge to meet on this screen will be the following:
- Validate user data and password
- When making a valid login, the home screen (bank account information and list of
movements) should be displayed.
- When pressing the registration option, the registration screen should be displayed,
which will be the beginning of the Onboarding.

The registration process must consist of the following views:
- User data: Email, password, name, last name
- Take ID photo: Take a picture of the ID.
- Success screen: Show a success message “Tu cuenta ha sido creada
exitosamente”.
- Home Screen: It will be the main screen, the one that the user accesses once he
makes a correct login.
- The data collected in the Onboarding must be stored in a database that the
candidate must create in Firebase.
- The candidate must also create test data in Firebase with movements to be able to
perform a user login without having to create an account.

## :paperclip:  Technologies and topics used

### Technologies
- Kotlin
- Corrutines
- Hilt
- Firebase (Storage, Auth, Cloud DataBase)
- Compose
- Data Storage Preferences
- Coil
- mockitoKotlin
- material3
- devtools-ksp
- google-services

### Topics
- Clean Architecture
- Clean Code
- SOLID
- MVVM patten
- Repository pattern
- Dependency injection

## :floppy_disk: Firebase

### Firebase Authentication
To manage user login, Firebase Authentication was used with email and password.

<img src="https://github.com/user-attachments/assets/e3c37a8c-4d05-47eb-a7f5-2f63dff16728" width="720"> 

### Firebase Storage
All user picture identification are stored in Firebase Storage, and the picture url is saved in Firestorage like user data

<img src="https://github.com/user-attachments/assets/75ccf73c-d8a3-4d71-907a-2dd352a99647" width="720"> 


### Firestore
Now to store the user data Firestore is used, having a collection `users` to save the user data and another collection intended `transactions` to save the movements

**Users table**

<img src="https://github.com/user-attachments/assets/5135ec5e-93fc-4326-a683-b7ba1dbaadf2" width="720"> 


**Transactions table**

<img src="https://github.com/user-attachments/assets/d9aef3d3-7250-4468-adc6-b4161a244ac3" width="720"> 

## :art: UI Test
### Onboarding
<img src="https://github.com/user-attachments/assets/99968560-7114-405c-83c8-c47caf8b3a0a" width="320"> 

### Sign In

Sign In Success | Some Fiel is Wrong | User Invalid
--- | ---  | --- 
<img src="https://github.com/user-attachments/assets/b79689b1-4c97-471c-b1d6-26b94f12a44a" width="220"> | <img src="https://github.com/user-attachments/assets/07c16f0f-29fd-4e4a-bfa9-6bb2fdd7c790" width="220"> | <img src="https://github.com/user-attachments/assets/9ed7d7c5-f6b9-44cf-8fa5-c7fb86ea009b" width="220">

### Sign Up

<img src="https://github.com/user-attachments/assets/e702c970-ca37-4cfa-a9a2-5070fc2fb099" width="320">

Sign Up Form Success | Sign Up Form is Wrong | User al readey Exist
--- | ---  | --- 
<img src="https://github.com/user-attachments/assets/fa246feb-de53-4ed4-a020-11891f92ef23" width="220"> | <img src="https://github.com/user-attachments/assets/cdf3c397-e4cc-4a43-957f-3654694b09dd" width="220"> | <img src="https://github.com/user-attachments/assets/36769115-20f4-46f8-996e-466f6cc0d886" width="220">

### Home

Home New User | Home New With Transactions
--- | --- 
<img src="https://github.com/user-attachments/assets/40588a82-501a-4a5a-afee-74b0b017d7f1" width="220"> | <img src="https://github.com/user-attachments/assets/66aca275-2804-4c2c-bb27-7ce1eda512a0" width="220"> 

### Profile
<img src="https://github.com/user-attachments/assets/0d4dff8a-c643-4f91-b17a-a4119f03bb18" width="320"> 

### Movement Detail



## :green_heart: How did you test it?

To test and see movements you can use the following credentials

```
email: edgar.barcenas@stori.mx
password: Edgar15_
```
You can also create a user from scratch to test Sign Up


**Also you can run the Unit Tests, recommended command:**

```
/gradlew test
```

