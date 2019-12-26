# MAC-Facility-Management-System

UTA Mac Facility Maintenance System:

System Users: Admin, User, Facility Manager, and Repairer.

Facility search. The user shall be able to select from the type of facility (Multipurpose room, Indoor basketball court, Indoor volleyball court, Indoor soccer gymnasium, racquetball court, badminton court, table tennis, conference room, outdoor volleyball court, or outdoor basketball court), the date (defaults to today), the start time (defaults to now) and see a report of available facilities for that type.

Available facility report. The system shall show the availability of facilities as follows (the following is a selectable list) to the user:
1.	Date
2.	Time slot
3.	Type of facility requested
4.	Facility Name (list them in ascending numeric order)
5.	Deposit

Notice that the Facility Manager may need to see additional information.
Once the user has selected a specific facility the system assigns the first available numeric facility number (e.g., CR 2). The reservation is not considered complete until it is paid for.
Four different kinds of users for the system:
1.	Facility manager 
a.	creates own profile
b.	views numbers of facilities available by type, date, and time
c.	views details of a specific facility
d.	makes a facility unavailable
e.	adds a new facility
f.	set no-show (sets a user to no-show)
g.	assigns a MAR to a repair staff
h.	searches assigned repairs by date or repairer.
i.	views repairers schedules
j.	update his own profile
2.	User (student, faculty, and staff)
a.	creates own profile
b.	reports problems
c.	update profile
3.	Repairer
a.	requests reservation (to repair a facility)
b.	view my reserved repairs
c.	modify my reserved repairs
d.	cancel my reserved repairs
e.	update profile
4.	Admin
a.	creates own profile
b.	edit user profile
c.	change user roles

All users will have the standard functions like register, login, logout. Each system user must register and for simplicity selects their role during registration. Assume that a single user that has multiple roles, e.g. User and Facility manager would register twice with different user names - the only affect of this rule is that each user name is unique and may be used as a Primary key in the database. Each user registers with at least a user name, role, UTA id, and personal details, contact information.
