DROP TABLE IF EXISTS "EVENT_SPONSOR";
DROP TABLE IF EXISTS "EVENT";
DROP TABLE IF EXISTS "SPONSOR";

CREATE TABLE SPONSOR (
    sponsorid INT PRIMARY KEY AUTO_INCREMENT,
    sponsorname TEXT,
    INDUSTRY TEXT
);
CREATE TABLE EVENT (
    EVENTID INT PRIMARY KEY AUTO_INCREMENT,
    EVENTNAME TEXT,
    "DATE" TEXT
);
CREATE TABLE EVENT_SPONSOR (
    SPONSORID INT,
    EVENTID INT,
    PRIMARY KEY (SPONSORID, EVENTID),
    FOREIGN KEY (SPONSORID) REFERENCES SPONSOR(sponsorid),
    FOREIGN KEY (EVENTID) REFERENCES "EVENT"(eventid)
);