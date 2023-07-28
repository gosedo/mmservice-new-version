use mmsystem;
START TRANSACTION;

INSERT INTO mmspropertymanagements(pMgmtCode, pMgmtName,	pMgmtAddrLine1,	pMgmtAddrLine2,	pMgmtAddrLine3,	pMgmtCity,
	pMgmtState,	pMgmtZipcode5, pMgmtZipcodeExt, pMgmtPhone,	pMgmtEmail,	pMgmtFax, pMgmtContact)
VALUES
('SMGMT','Southern Managment','220 S Eden st','','','Columbia','MD','55401','2312','4103459867','info@southerMngmt.com','4103459868','Ashley'),
('PPMGT','Professional Managment','230 S Eden st','','','Columbia','MD','55401','2312','4103459827','info2@southerMngmt.com','4103459869','Nashley');

INSERT INTO mmsproperties(proCode, proName,	proAddrLine1, proAddrLine2,	proAddrLine3, proCity, proState, 
	proZipcode5, proZipcodeExt,	proPhone, proEmail,	proFax,	contact, proMgmtID)
VALUES ('PKCH','Park Trees','435 N Metro blvd', 'suit 100','','Baltimore','MD','34534','','4109874565','parkt@parttress.com','4109874563','Brown',1),
('QYCH','Queens York','435 N Luther blvd', 'suit 101','','Baltimore','MD','34534','','4109874567','queeny@parttress.com','4109874562','Saron',2);


INSERT INTO mmsunits(unitCode,	unitType, unitName,	unitAddrLine1, unitAddrLine2, unitAddrLine3, unitCity, unitState,
	unitZipcode5, unitZipcodeExt, belongToPropId )
VALUES
('220-QWTR-1102','APT','220 Light Street 1102','220 Light st', 'Apt 1102','','Baltimore', 'MD', '34534', '',1),
('220-QWTR-1103','APT','220 Light Street 1103','220 Light st', 'Apt 1103','','Baltimore', 'MD', '34534', '',1),
('220-QWTR-1104','APT','220 Light Street 1104','220 Light st', 'Apt 1104','','Baltimore', 'MD', '34534', '',1),
('220-QWTR-1105','APT','220 Light Street 1105','220 Light st', 'Apt 1105','','Baltimore', 'MD', '34534', '',1),
('220-QWTR-1106','APT','220 Light Street 1106','220 Light st', 'Apt 1106','','Baltimore', 'MD', '34534', '',1),
('220-QWTR-1107','APT','220 Light Street 1107','220 Light st', 'Apt 1107','','Baltimore', 'MD', '34534', '',1),
('220-QWTR-1108','APT','220 Light Street 1108','220 Light st', 'Apt 1108','','Baltimore', 'MD', '34534', '',1)
;

INSERT INTO mmsuserstatus(userStatusCode, userStatusDescr)
VALUES
('A','Active'),
('T','Terminated');

INSERT INTO mmsuserroles(usrRoleCode, usrRoleDescr)
VALUES
('TNNT','Tenant'),
('TECH','Technician'),
('MGMT','Management');



INSERT INTO mmsusers(userEmail, userPassword, userFirstname, userLastname, userPhone, userStatusId)
VALUES
('phenderson@randatmail.com' 	, '7b09hy', 'Penelope' 	, 'Henderson'	,	'259-6277-1868',1,'N',null),	
('akelley@randatmail.com'		, 'lew9m5', 'Amber'		, 'Kelley'		,   '692-6874-5059',1,'N',null),
('lbarnes@randatmail.com'		, 'lw2w1x', 'Luke'		, 'Barnes'		,   '142-5179-6543',1,'N',null),	
('tdavis@randatmail.com'		, 'k6x16i', 'Tess'		, 'Davis'		,   '661-8520-4942',1,'N',null),	
('hfoster@randatmail.com'		, 'ywbsg6', 'Honey'		, 'Foster'		,   '123-1837-1641',1,'N',null),
('hbrown@randatmail.com'		, 'erbsg6', 'Henery'	, 'Brown'		,   '434-1847-1241',1,'N',null),
('lbarnesere@randatmail.com'		, 'ws2w1x', 'Tuke'		, 'Boarnes'		,   '176-5179-6543',1,'N',null),	
('tdavissec@randatmail.com'		, 'rfx16i', 'Less'		, 'Travis'		,   '698-8520-4942',1,'N',null),	
('hfosterwed@randatmail.com'		, 'edbsg6', 'Koney'		, 'Monster'		,   '165-1837-1641',1,'N',null),
('hbrownwsde@randatmail.com'		, 'websg6', 'Nenery'	, 'Aswown'		,   '423-1847-1241',1,'N',null);

INSERT INTO mmsusers_mmsuserroles(userId, usrRoleId)
VALUES
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,2),
(8,2),
(9,3),
(10,3)
;

INSERT INTO mmstenants(tenantInfo_userId , tenantUnit_unitId, isCurrent, createdOnDate, statusModifiedDate)
VALUES
(1, 1, 1, now(), now() ),
(2, 2, 1, now(), now() ),
(3, 3, 1, now(), now() ),
(4, 4, 1, now(), now() )
;

-- https://www.buildium.com/blog/property-maintenance-services-checklist-guide/
INSERT INTO mmsissuetypes(issueTypeCode, issueTypeDescr)
VALUES
('HVAC','Heating or Ventilation or AC'),
('PLUM','Plumbing systems'),
('ELEC','Electrical systems'),
('HMAP','Home appliances'),
('WNDR','Windows and Doors'),
('FLCR','Flooring and Carpets'),
('OUTD','Outdoor amenities'),
('SMKD','Smoke detectors'),
('OTHR','Others')
;

INSERT INTO mmsissuestatus(issueStatusCode, issueStatusDescr)
VALUES
('NEWR','New Request'),
('UDRV','Under Review'),
('INPR','In Progress'),
('ONHL','On Hold'),
('CLSD','Closed'),
('CMPL','Completed')
;

-- TRUNCATE TABLE maintenanceissues;

INSERT INTO mmsmaintenanceissues(issueTypeId, issueStatusId, issueDescription, issueReqTenantId, createdOnDate, completedOnDate )
VALUES
(1, 1, 'AC not working. Too hot!',1, now(), date_add(now(), INTERVAL 5 HOUR)),
(2, 1, 'Toilet not flushing!',2, date_add(now(), INTERVAL 1 HOUR), date_add(now(), INTERVAL 6 HOUR)),
(3, 1, 'Kitchen light out!',3, date_add(now(), INTERVAL 2 HOUR), date_add(now(), INTERVAL 8 HOUR))
;

INSERT INTO mmsposts(comment, issueId, postUserId,createdOn )
VALUES
('Unable to access unit. Please move blocking items.',1,8,now()),
('Items moved.',1,8,now())
;

INSERT INTO mmstechteams(techTeamDescr)
VALUES
('HVAC Team'),
('Electricians'),
('Plumbing Team');

INSERT INTO mmstechteammembers(memberUserId,isLead)
VALUES
(7,'N'),
(8,'N')
;

INSERT INTO mmstechteammembers_mmstechteams(memberID, memberOfTeamId)
VALUES
(1,1),
(2,1)
;

INSERT INTO mmstechtasktatus(taskStatusCode, taskStatusDescr)
VALUES
('NEWR','New Request'),
('UDRV','Under Review'),
('INPR','In Progress'),
('ONHL','On Hold'),
('CLSD','Closed'),
('CMPL','Completed')
;

INSERT INTO mmstechtasks(taskIssueId,taskDescr,taskStatusId,techTeamId,createdById,createdOnDate,taskClosedById,closedOnDate)
VALUES
(1,'Add freon','2',1,9,now(),7,null)
;

INSERT INTO mmstechtaskposts(techTaskId,comment,userId,createdOn)
VALUES
(1,'Will be done in an hour',7,now())
;

COMMIT;



