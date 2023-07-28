Maintenance Management Service (MMService)

This is one of the microservices that is planned to be included in the whole system that will serve as a property management system.
The Property management system will have Maintenance Management Service, Inventory Management Service, Contract/Lease Management Service and Billing Service.

MMService at the current implementation stage (which is done for capstone) just has the feature necessary to meet the capstone requirement. But when the whole project gets completed, it will have more features and gives more services than what it has at this moment. 

Current Features
	Maintenance Issue Management Feature
	Tasks Management Feature
	User Management Feature
	
Maintenance Issue Management Feature
	Users
		Tenants
		Maintenance Managers
		Technicians
	
		
Activities
===========

	Tenant
	=========
	As the user of the system will be able to submit new maintenance issues.
	They will be able to check the status.
	
	Maintenance Manager
	================= 
		Issues and Task related activities by managers
			As the user of the service managers will be able to review the issues submitted.
			Will be able to change the status.
			Will be able to recategorize the issue type if the submission was to the wrong category.
			Will be able to create task/s to address the issue.
			Will be able to assign TechTeam to the Task.
			Will be able to delete task.
			Will be able to review issues and the task/s created for the issues.
			Will have the ability to change the status of the task/s.
			Will have the ability to change the status of issue based on the task/s statuses of the task/s.
			Will have the ability to manage users.
		Users management
			Only managers will be able to manage the users of the service.
			Will be able to register users.
			Will be able to review users.
			Will be able to update user information.
			Will be able to notify users about the new registration sending email.
			Will be able to notify users about the update in their information.
			Will be able to send email with a link for account activation and change of password after registration and update to the account.
	
	Technicians 
	===========
			Will be able to review issues.
			Will be able to review task/s.
			Will be able to update the task status.


Technical Implementation
==========================
Spring Boot REST API and Angular are used as back end and front end technology to implement the MMService.

Spring Boot REST API
======================
This is the back end of the MMService application.
It is composed of the following packages.

Com.mmssystem.property –> the root package with the main spring boot application class and custom configuration class.

Com.mmssystem.property.controller –> the controllers for REST APIs package

			AuthenticationController – The login functionality of the API.
			=======================
			A request to baseurl/api/auth/signin goes through spring security for authentication. Once authentication done and security context set, it will be allowed to access this API. In this the API, jwt bearer token will be generated based on the username/email and security key, and a response will be composed as AuthInformationDTO which has user information and token and token expiration date. Clients have to provide the bearer token in order to access resources which are set only for authorized clients.
			 
			MmsIssueController 
			=================== 
			/***
			* Maintenance Issue related service request will be handled with this controller.
			* will be able to create, select, update issues. Paged requests and responses are handled
			* 
			* */
			
			MmsTechTaskController – 
			====================
			/**
			* Task related request and response handled here.
			* create, select, update and delete request and response
			* 
			* */
			
			
			StaticDataController -
			=====================
			/**
			* This just for angular client application to provide static data/non frequent changing data
			* so that the angular application don't have to make multiple trip to the api to get non changing data. 
			* */
			UserController
			==============
			/**
			* User related request and response are handled here.
			* create, select, update and delete can be handled her.
			* */
			


Com.mmssystem.property.dto – the data transfer objects package

			The purpose of these object are just to transfer data between clients and the service layer. Sample dtos
			AuthInformationDTO
			=================
			/**
			* Used as transfer object by the authentication controller to send response with bearer token and user information
			* */
			MmsIssueCreateDTO
			==================
			/**
			* Used to take information necessary to create maintenance issue request by the tenant
			* */
			MmsIssueUpdateDTO
			================
			/**
			*Used to take information necessary to update maintenance issue request 
			* 
			* */
			MmsTechTaskCreateDTO
			===================
			/**
			*Used to take in the necessary information to create tasks 
			* */
			MmsUserActivationDTO
			====================
			
			/**
			* Used to take in user new password change information for activation
			* */


Com.mmssystem.property.exception – global exception handling and related classes

			MmsCustomError
			=============
			/**
			* This will be the response body for errors and exceptions
			* It has the time error occurred, the type Http Status we want to send the client
			* and the custom messaged/ application related messages can be included 
			* Validation errors list can be included.
			* */
			MmsCustomResponseBuilder
			=======================
			/**
			* This just custom builder not to repeat the creation of new ReponseEntity object everywhere.
			* */
			
			MmsGlobalExceptionHandler
			========================
			/**
			* This is a global exception handler to centralize the exception handling in spring boot application.
			* Help us have a generic exception handlers but message are localized to make them specific and
			* application related. And also have control the type of http status that will be in response.
			* 
			* */
			ResourceAlreadyExistsException
			============================
			
			/**
			* This is a generic exception that we can use where ever we want to give application specific 
			* exception information for users attempting to create the resources already in the system
			* The message can be customized as per the resources.
			* */
			ResourceNotFoundException
			========================
			/**
			* This is a generic exception that we can use whereever we want to give application specific 
			* exception information for users attempting to modify/do anything on non existing resources.
			* The message can be customized as per the resources.
			* */


Com.mmssystem.property.mapper – MapStruct is being used to map entities and dto. Below sample mappers.

			MmsIssueStatusMapper
			====================
			/**
			* MmsIssueStatus <-> MmsIssueStatusDTO mapper 
			* */
			
			MmsIssueTypeMapper
			==================
			/**
			* MmsIssueType <-> MmsIssueTypeDTO mapper
			* */


Com.mmssystem.property.model – entities for the MMService are in these package
			MmsIssueStatus
			MmsIssueType
			MmsMaintenanceIssue
			MmsProperty
			MmsPropertyManagement
			MmsTechTask
			MmsTechTaskStatus
			MmsTechTeam
			MmsTechTeamMember
			MmsTenant
			MmsUnit
			MmsUser
			MmsUserRole
			MmsUserStatus


Com.mmssystem.property.repo – repositories for the service. Some are listed below.

			MmsIssuesJPARepository – JPA based repository
			=====================
			MmsIssuesRepository - spring hibernate with custom config
			===================
			/**
			* Still in migration to JPA but used with custom config.
			* It used SessionFactory bean for hibernate for database related operations 
			* */
			MmsIssueStausRepository
			========================
			/**
			* Still in migration to JPA but used with custom config.
			* It used SessionFactory bean for hibernate for database related operations. 
			* Used for issues statuse
			* */
			MmsIssueStausJPARepository
			==========================
			
			etc...


Com.mmssystem.property.security – spring security with custom security configuration and custom usersdetail service

			CustomUserDetailsService
			========================
			/**
			* This is a custom user detail service that implements spring UserDetailsService
			* and provide user information(user detail) based on the username (which is the email) of the user
			* to the custom authentication provider. The provider relies on loadUserByUsername()
			* method of the user detail service for authentication.
			* 
			* */
			CustomAuthenticationProvider
			============================
			/**
			* This bean presents itself as provider so that the authentication manager will use it 
			* as authentication provider. As AuthManger provided by spring boot which will be used by 
			* the custom filter will go through possible candidates of auth provide which this is one of them.
			* authenticate method uses loadUserByUsername()of user detail service to get user password
			* and matches with the incoming one, if equal, returns Authentication object
			* 
			* */
			CustomTokenFilter
			=================
			/**
			* This custom filter when added to the filter list it will be responsible for security
			* related actions.
			* It handles basic and bearer token authentication. If basic authentication it extract user information base64
			* token and do authentication. If bearer token it checks the validity of the token. And then set the security context 
			* with authenticate. The security context will be used for the authorization of access to the resources.
			* */
			CustomSecurityConfig
			====================
			/**
			* Spring boot uses this custom configuration for custom security filter chain,
			* for setting/providing security bean like passwordEncoder, CORS configuration,spring auth managers
			* authorization to requests, etc... 
			* 
			* */


Com.mmssystem.property.service – MMService services are in this package

			MmsIssuesServiceImpl
			====================
			/**
			* This is services uses many other services to do its job related to issues.
			* used to create, find, update and delete issues.
			* It also has paged issue find method.
			* */
			MmsStaticDataServiceImpl
			=======================
			/**
			* Used to get static data for client application not to make multiple rounds
			* */
			
			MmsTechTaskServiceImpl
			======================
			/**
			* This is a service that provides task related operation.
			* It can be used to create, find, update and delete task/s. 
			* */
			MmsUserServiceImpl
			=================
			/**
			* User related operation. 
			* Registration, updating user information, calling other services to make user a tenant
			* , team member, email notification when new registration or update on existing user. 
			* Create, find, update
			* 
			* */



Com.mmssystem.property.util – various utility classes, constant values and email util service

			EmailUtil
			=========
			/**
			* Uses javaMailSender to send email when email detail provided.
			* */
			IssuesPageConstants
			===================
			/**
			* When issues paged request sent, if values are not provided, these values will be the default.
			* */
			MmsComposeEmailUtil
			===================
			/**
			* Used to compose different email content. E.g New Account Registration
			* Updated Account notification.
			* 
			* */
			MmsPageParam
			============
			/**
			* It is just acting as dto but for just parameters.
			* Rather than sending multiple parameter, putting them in one object and pass them.
			* 
			* */
			RoleTypeConstants
			=================
			/**
			* Used in codes when roleId are being compared. Just descriptive name.
			* */
			StubData
			========
			/**
			* sample data for testing purpose
			* */
			UsersPageConstants
			==================
			/**
			* Used by user services to pass values UserJPARepository for default values in the query.
			* */
			UserStatusesConstants
			====================
			/**
			* Descriptive way to represent user status in the code. Rather than using number use these names.
			* */
			YesOrNoConstants
			===============
			/**
			* Used in the places where values are Y or N.
			* */




