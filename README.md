# Welcome 



The API Dietta is an Application Programming Interface (API) that provides access to various features and data within the Dietta application. This API is designed to allow developers or third parties to connect to Dietta and integrate its health features into their own applications.

Here is a description of the main features that can be accessed through the API Dietta:

1.  User Feature: The Dietta API provides the ability to manage user information, such as user profiles, personal preferences, and health data. Developers can use this API to create, update, or delete user profiles, as well as access relevant user data.
    
2.  Video Feature: The Dietta API grants access to the video library provided by the Dietta application. Developers can use this API to display exercise videos, tutorials, or health guides within their own applications, allowing users to easily access valuable visual content.
    
3.  History Feature: Through the Dietta API, developers can access user historical data, such as exercise history, previous meal patterns, or other health records. This allows developers to display information about user progress or provide useful analytical features to track changes and health achievements.
    
4.  Schedule Feature: The Dietta API enables developers to manage user health activity schedules. Developers can use this API to create, edit, or delete exercise schedules, sleep routines, or other health-related activities. Users of the connected application can view and manage their schedules to maintain consistency and discipline in maintaining a healthy lifestyle.
    
5.  Culinary Feature: The Dietta API provides access to information and recommendations for healthy cuisine. Developers can use this API to display nutritionally balanced recipes, nutritional information, or guidance on good eating habits. This allows users of the connected application to gain inspiration in cooking healthy meals and expand their culinary choices.
    
6.  Diary Feature: Through the Dietta API, developers can provide a journal or health record feature to users. Users can record activities, food intake, sleep, or other health information in their personal diary. The Dietta API allows developers to store and access this data, as well as provide recommendations based on the information recorded in the user's diary.


# PREREQUIREMENT

 - Java SDK Version 20
 - Java Spring Boot
 - MySql Database

# API

	Eureka	localhost:8888	
	
	Akun	localhost:8080

 1. Create Akun	Api Untuk Membuat Akun Pengguna	**/user/add** 	
 2. Read List Akun	Api Untuk Membaca Daftar Akun Pengguna	**/user/all** 	
 3. Read Detail Akun	Api Unuk Membaca Detail Akun Pengguna	**/user/{id}**
 4. Update Akun	Api Untuk Memperbaruhi Akun Pengguna	**/user/update/{id}** 	
 5. Delete Akun	Api Untuk Menghapus Akun  Pengguna	**/user/{id}**


		Akun	localhost:8080


 7. Create Data Pedoman	Api Untuk Membuat Data Pedoman Hidup
    Sehat	**/api/video/add**
 8. Read List Data Pedoman	Api Untuk Membaca Daftar Pedoman Hidup
    Sehat	**/api/video/all**
 9. Read Detail Data Pedoman	Api Unuk Membaca Detail Pedoman Hidup
    Sehat	**/api/video/{id}**
 10. Update Data Pedoman	Api Untuk Memperbaruhi Pedoman Hidup
    Sehat	**/api/video/update/{id}**
 11. Delete Data Catatan Makanan	Api Untuk Menghapus Pedoman Hidup
    Sehat	**/video/delete/{id}**

	Diary	localhost:8282

 12. Create Catatan Data Makanan	Api Untuk Membuat Catatan Data
    Makanan	**/diary/add**
 13. Read List Catatan Data Makanan	Api Untuk Membaca Catatan Data
    Makanan	**/diary/all**
 14. Read Detail Catatan Makanan	Api Unuk Membaca Detail Catatan Data
    Makanan	**/diary/{id}**
 15. Update Data Catatan Makanan	Api Untuk Memperbaruhi Catatan Data
    Makanan	**/diary/update/{id}**
 16. Delete Data Catatan Makanan	Api Untuk Menghapus Catatan Data
    Makanan	**/diary/{id}**

 	History	localhost:8383

 17. Create History Olahraga	Api Untuk Membuat History
    Olahraga	**/history/add**
 18. Read List History Olahraga	Api Untuk Membaca Daftar History
    Olahraga	**/history/all**
 19. Read Detail History Olahraga	Api Unuk Membaca Detail History
    Olahraga	**/history/{id}**
 20. Delete History Olahraga	Api Untuk Memperbaruhi History
    Olahraga	**/history/{id}**

	Culinary	localhost:8484

 21. Create Rekomendasi Makanan Sehat	Api Untuk Membuat Rekomendasi
    Makanan Sehat	**/food/add**
 22. Read List Rekomendasi Makanan Sehat	Api Untuk Membaca Rekomendasi
    Makanan Sehat	**/food/all**
 23. Read Detail Rekomendasi Makanan Sehat	Api Unuk Membaca Detail
    Rekomendasi Makanan Sehat	**/food/{id}**
 24. Update Rekomendasi Makanan Sehat	Api Untuk Memperbaruhi Rekomendasi
    Makanan Sehat	/**food/update/{id}**
 25. Delete Rekomendisi Makanan Sehat	Api Untuk Menghapus Rekomendasi
    Makanan Sehat	**/food/{id}**

	Schedule	localhost:8585	
 26. Create Jadwal Olahraga	Api Untuk Membuat Jadwal Olahraga
    	**/api/schedule/add**
 27. Read List Jadwal Olahraga	Api Untuk Membaca Daftar Jadwal Olahraga
    	**/api/schedule/all**
 28. Read Detail Jadwal Olahraga	Api Unuk Membaca Detail Jadwal Olahraga
    	**/api/schedule/{id}**
 29. Update Jadwal Olahraga	Api Untuk Memperbaruhi Jadwal Olahraga
    	**/api/schedule/update/{id}**
 30. Delete Jadwal Olahraga	Api Untuk Menghapus Jadwal Olahraga
    	**/api/schedule/{id}**
