CREATE TABLE election (
    id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

CREATE TABLE election_candidate (
    election_id VARCHAR(40) NOT NULL,
    candidate_id VARCHAR(40) NOT NULL,
    votes INTEGER DEFAULT 0,
    PRIMARY KEY(election_id, candidate_id)
);

-- SEED: https://www.mockaroo.com
INSERT INTO candidates (id, photo, given_name, family_name, email, phone, job_title) VALUES
('e4f21dc6d5478ed70ce1c08e13f2f637c6ac1eaa', 'http://dummyimage.com/164x100.png/dddddd/000000', 'Nevsa', 'Jailler', 'njailler0@51.la', '208-576-2289', 'Senior Developer'),
('0e88705194fa3a998b7eda0914472fe9b783dd90', null, 'Dennie', 'McGivena', 'dmcgivena1@census.gov', '226-858-7022', 'Sales Associate'),
('f600ecaa6ff244bca5349ecfba720e9e642734c6', 'http://dummyimage.com/139x100.png/dddddd/000000', 'Nollie', 'Motion', 'nmotion2@adobe.com', null, 'Budget/Accounting Analyst I'),
('66d76f465bde61a3683067dbe288f29e46a98610', 'http://dummyimage.com/104x100.png/5fa2dd/ffffff', 'Sapphira', 'Hurling', 'shurling3@ovh.net', '319-277-0333', 'Legal Assistant'),
('26f9d82bb7c2ac24af3bc8a471972b1302fc7c89', 'http://dummyimage.com/197x100.png/ff4444/ffffff', 'Lynne', 'Childs', 'lchilds4@nytimes.com', '984-769-4139', 'VP Marketing'),
('ef6fd8d982509fe8ec1f9c4e7d6c3eccac7f6c41', 'http://dummyimage.com/106x100.png/cc0000/ffffff', 'Cathie', 'Sedgebeer', 'csedgebeer5@artisteer.com', '559-780-4470', 'Web Designer IV'),
('dbfd6a2bfbd5c942b8fc6133c25d31d8a978c12f', 'http://dummyimage.com/185x100.png/5fa2dd/ffffff', 'Ammamaria', 'Kalberer', 'akalberer6@sfgate.com', '751-926-1356', 'Computer Systems Analyst II'),
('329dac895360acad701a87aa2e0782c1c48baf84', null, 'Perry', 'Birch', 'pbirch7@geocities.com', '666-366-5359', null),
('ce3bde5cc52900dc98108ea06a478cd9ef82bb3e', 'http://dummyimage.com/240x100.png/dddddd/000000', 'Jerrylee', 'Tann', 'jtann8@biglobe.ne.jp', '138-934-1445', 'Analog Circuit Design manager'),
('3b03937719dc0dfc2770eb8413effa2d1d41ca33', null, 'Thadeus', 'Simmen', 'tsimmen9@i2i.jp', '352-409-1493', 'GIS Technical Architect'),
('b9f6403d6c455dc88903c926b76b6ee5808697c1', 'http://dummyimage.com/156x100.png/cc0000/ffffff', 'Shandie', 'Wisson', 'swissona@soup.io', null, 'Information Systems Manager'),
('c6d01f689867da3b704f6c68330525ee444b2841', 'http://dummyimage.com/216x100.png/ff4444/ffffff', 'Winnah', 'Gain', 'wgainb@hexun.com', '466-716-1861', 'Pharmacist'),
('f1aa074fe44c189bf8f89fba3ed2b7488d3c6f7f', 'http://dummyimage.com/224x100.png/ff4444/ffffff', 'Sherlock', 'Kippling', 'skipplingc@princeton.edu', '487-879-6222', 'Pharmacist'),
('dc28068cc403d33ecc0f60d1d587a44ee499b80f', 'http://dummyimage.com/213x100.png/ff4444/ffffff', 'Damon', 'Shallow', 'dshallowd@cafepress.com', '919-795-3925', null),
('0983c91b18b277a14e326c872de01d9ea7bb37d5', 'http://dummyimage.com/202x100.png/5fa2dd/ffffff', 'Gaylene', 'Bolesworth', 'gbolesworthe@pagesperso-orange.fr', '419-826-0113', 'Human Resources Assistant II'),
('1fab74dfab20b42629d6e6593847173feca29c46', null, 'Anetta', 'Bush', 'abushf@behance.net', '775-542-8930', null),
('07838128d3b2da54c347ea7a2aec45dc18a7acfe', 'http://dummyimage.com/102x100.png/dddddd/000000', 'Charlot', 'Casino', 'ccasinog@geocities.jp', '596-533-1593', null),
('08c2763aa6f0e4269bd98556af3596bb95cc3f0b', 'http://dummyimage.com/138x100.png/5fa2dd/ffffff', 'Kalli', 'Conigsby', 'kconigsbyh@theguardian.com', '870-515-6988', 'Marketing Assistant'),
('2c6c87a532bbb08f05896a5d2cdeba38f7d200a5', 'http://dummyimage.com/101x100.png/5fa2dd/ffffff', 'Lannie', 'Wentworth', 'lwentworthi@unicef.org', '346-324-3664', null),
('90ff8d4821963d8468a0b47b95448e97007f5b65', 'http://dummyimage.com/164x100.png/5fa2dd/ffffff', 'Meggy', 'Coyne', 'mcoynej@pbs.org', '283-233-0726', 'Media Manager II');