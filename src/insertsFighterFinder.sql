INSERT INTO `profile` (profiletype) VALUES
	("admin"), ("user");

INSERT INTO `game` (name) VALUES
	("Street Fighter V"), ("BlazBlue Centralfiction"), ("Guilty Gear Xrd - Revelator"), ("Super Smash Bros. for Wii u"), ("Super Smash Bros. Melee"),("Killer Instinct"), ("Mortal Kombat X"), ("Skullgirls"), ("Under Night In-Birth: Exe Late"), ("Arcana Heart 3: Love Max"), ("Dengeki Bunko Fighting Climax: Ignition"), ("Melty Blood: Actress Again Current Code"), ("Street Fighter III: 3rd Strike"), ("Super Street Fighter 2 Turbo"), ("Ultimate Marvel vs Capcom 3"), ("Tekken Tag Tournament 2");

INSERT INTO `objective` (message) VALUES
	("Searching for friends"), ("Having fun"), ("Compete"), ("I want to be the very best");
	
INSERT INTO `user` (nick, email, password, skill, id_profile, id_objective) VALUES 
	("FFAdmin", "ffadmin@gmail.com", "admin@pass1234", 5, 1, 1);
INSERT INTO `user` (nick, email, password) VALUES
        ("SomeTest", "sometest@gmail.com", "sometest1234"), ("Person", "personemail@gmail.com", "person1234"), ("Test1", "testemail@gmail.com", "test11234"), ("Test2", "test2email@gmail.com", "test21234"), ("Test0", "test0email@gmail.com", "test01234");

INSERT INTO `character` (name, id_game) VALUES
	("Ryu", 1), ("Ken", 1), ("Chun-Li", 1), ("Cammy", 1), ("Dhalsim", 1), ("M. Bison", 1), ("Vega", 1),  ("Zangief", 1), ("Nash", 1), ("Karin", 1), ("Birdie", 1), ("R. Mika", 1),
        ("Laura", 1), ("Necalli", 1), ("Rashid", 1),  ("F.A.N.G", 1), ("Alex", 1), ("Guile", 1),
        ("Amane Nishiki", 2), ("Arakune", 2), ("Azrael", 2), ("Bang Shishigami", 2), ("Bullet", 2), ("Carl Clover", 2), ("Celica A. Mercury", 2), ("Es", 2), ("Hakumen", 2), 
        ("Hazama", 2), ("Hibiki Kohaku", 2), ("Iron Tager", 2), ("Izanami", 2), ("Izayoi", 2), ("Jin Kisaragi", 2), ("Kagura Mutsuki", 2), ("Kokonoe", 2), ("Litchi Faye Ling", 2), 
        ("Makoto Nanaya", 2), ("Naoto Kurogane", 2), ("Nine the Phantom", 2), ("Noel Vermillion", 2), ("Platinum the Trinity", 2), ("Rachel Alucard", 2), ("Ragna the Bloodedge", 2), 
        ("Relius Clover", 2), ("Taokaka", 2), ("Tsubaki yayoi", 2), ("Valkenhayn R. Hellsing", 2), ("Yuuki Terumi", 2), ("Λ-11", 2), ("μ-12", 2), ("ν-13", 2),
        ("Axl Low", 3), ("Bedman", 3), ("Chipp Zanuff", 3), ("Dizzy", 3), ("Elphet Valentine", 3), ("Faust", 3), ("I-No", 3), ("Jack-O", 3), ("Jam Kuradoberi", 3), ("Johnny", 3),
        ("Kum Haehyun", 3), ("Ky Kiske", 3), ("Leo Whitefang", 3), ("May", 3), ("Millia Rage", 3), ("Potemkin", 3), ("Ramlethal Valentine", 3), ("Raven", 3), ("Sin Kyske", 3),
        ("Slayer", 3), ("Sol Badguy", 3), ("Venom", 3), ("Zato-1", 3),
        ("Mario", 4), ("Luigi", 4), ("Peach", 4), ("Bowser", 4), ("Dr. Mario", 4), ("Yoshi", 4), ("Donkey Kong", 4), ("Diddy Kong", 4), ("Link", 4), ("Zelda", 4), ("Sheik", 4),
        ("Ganondorf", 4), ("Toon Link", 4), ("Samus", 4), ("Zero Suit Samus", 4), ("kirby", 4), ("Meta Knight", 4), ("King Dedede", 4), ("Fox", 4), ("Falco", 4), ("Pikachu", 4),
        ("Jigglypuff", 4), ("Mewtwo", 4), ("Charizard", 4), ("Lucario", 4), ("Captain Falcon", 4), ("Ness", 4), ("Lucas", 4), ("Marth", 4), ("Roy", 4), ("Ike", 4), ("Mr. Game Watch", 4),
        ("Pit", 4), ("Wario", 4), ("Olimar", 4), ("R.O.B.", 4), ("Sonic", 4), ("Rosalina And Luma", 4), ("Bowser Jr.", 4), ("Greninja", 4), ("Robin/Daraen", 4), ("Lucina", 4),
        ("Palutena", 4), ("Dark Pit", 4), ("Villager", 4), ("Little mac", 4), ("Wii Fit Trainer", 4), ("Shulk", 4), ("Duck Hunt", 4), ("Mega Man", 4), ("Pac-Man", 4), ("Ryu", 4),
        ("Cloud", 4), ("Bayonetta", 4), ("Mii Brawler", 4), ("Mii Swordfighter", 4), ("Mii Gunner", 4),
        ("Mario", 5), ("Luigi", 5), ("Yoshi", 5), ("Donkey Kong", 5), ("Link", 5), ("Samus", 5), ("Kirby", 5), ("Fox", 5), ("Pikachu", 5), ("Jigglypuff", 5), ("Captain Falcon", 5),
        ("Peach", 5), ("Bowser", 5), ("Dr. Mario", 5), ("Zelda", 5), ("Sheik", 5), ("Ganondorf", 5), ("Young Link", 5), ("Falco", 5), ("Mewtwo", 5), ("Pichu", 5), ("Ice Climbers", 5),
        ("Marth", 5), ("Roy", 5), ("Mr. Game Watch", 5),
        ("Aganos", 6), ("Arbiter", 6), ("Aria", 6), ("Cinder", 6), ("Fulgore", 6), ("Glacius", 6), ("Hisako", 6), ("Jago", 6), ("Kan-Ra", 6), ("Kim-Wu", 6), ("Maya", 6), ("Mira", 6),
        ("Omen", 6), ("Orchid", 6), ("Rash", 6), ("Riptor", 6), ("Sabrewulf", 6), ("Sadira", 6), ("Shadow Jago", 6), ("Spinal", 6), ("Thunder", 6), ("Thunder", 6), ("TJ Combo", 6),
        ("Tusk", 6),
        ("Alien", 7), ("Bo'Rai Cho", 7), ("Cassie Cage", 7), ("D'Vorah", 7), ("Ermac", 7), ("Erron Black", 7), ("Ferra/Torr", 7), ("Goro", 7), ("Jacqui Briggs", 7), ("Jason", 7),
        ("Jax", 7), ("Johny Cage", 7), ("Kano", 7), ("Kenshi", 7), ("Kitana", 7), ("Kotal Khan", 7), ("Kung Jin", 7), ("Kung Lao", 7), ("Leatherface", 7), ("Liu Kang", 7),
        ("Mileena", 7), ("Predator", 7), ("Quan Chi", 7), ("Raiden", 7), ("Reptile", 7), ("Scorpion", 7), ("Shinnok", 7), ("Sonya", 7), ("Sub-Zero", 7), ("Takeda", 7),
        ("Tanya", 7), ("Tremor", 7), ("Triborg", 7),
        ("Beowulf", 8), ("Big Bang", 8), ("Cerebella", 8), ("Double", 8), ("Eliza", 8), ("Filia", 8), ("Fukua", 8), ("Ms. Fortune", 8), ("Painwheel", 8),  ("parasoul", 8), ("Peacock", 8),
        ("Robo-Fortune", 8), ("Squigly", 8),
        ("Akatsuki", 9), ("Byakuya", 9), ("Carmine", 9), ("Chaos", 9), ("Eltnum", 9), ("Gordeau", 9), ("Hilda", 9), ("Hyde", 9), ("Linne", 9), ("Merkava", 9), ("Nanase", 9),
        ("Orie", 9), ("Phonon", 9), ("Seth", 9), ("Vatista", 9), ("Waldstein", 9), ("Yuzuriha", 9),
        ("Akane Inuwaka", 10), ("Angelia Avallone", 10), ("Catherine Kyohnashi", 10), ("Clarice Di Lanza", 10), ("Dorothy Albright", 10), ("Eko", 10), ("Elsa La Conti", 10),
        ("Fiona Mayfield", 10), ("Heart Aino", 10), ("Kamui Tokinomiya", 10), ("Kira Daidohji", 10), ("Konoha", 10), ("Liselotte Achenbach", 10), ("Lilica Felchenerow", 10),
        ("Maori Kasuga", 10), ("Mei-Fang", 10), ("Nazuma Inuwaka", 10), ("Petra Johanna Lagerkvist", 10), ("Saki Tsuzura", 10), ("Scharlachrot", 10), ("Weiß", 10), ("Yoriko Yasuzumi", 10),
        ("Zenia Valov", 10),
        ("Akira Yuki", 11), ("Ako", 11), ("Asuna", 11), ("Emi Yusa", 11), ("Kirino Kosaka", 11), ("Kirito", 11), ("Kuroko Shirai", 11), ("Kuroyukihime", 11), ("Mikoto Misaka", 11),
        ("Miyuki Shiba", 11), ("Qwenthur Barbotage", 11), ("Rentaro Satomi", 11), ("Selvaria Bles", 11), ("Shana", 11), ("Shizuo Heiwajima", 11), ("Taiga Aisaka", 11), ("Tatsuya Shiba", 11),
        ("Tomoka Minako", 11), ("Yukina Himeragi", 11), ("Yuuki", 11),
        ("Akiha", 12), ("Akiha(Seifuku)", 12), ("Akiha Vermillion", 12), ("Aoko", 12), ("Archetype: Earth", 12), ("Arcueid", 12), ("Ciel", 12), ("Hisui", 12), ("Hisui And Kohaku", 12),
        ("Kohaku", 12), ("Kohaku And Mech-Hisui", 12), ("Kouma", 12), ("Len", 12), ("Mech-Hisui", 12), ("Mech-Hisui And Neco Arc", 12), ("Miyako", 12), ("Neco Arc", 12), 
        ("Neco Arc Chaos", 12), ("Nero", 12), ("Power Ciel", 12), ("Red Arcueid", 12), ("Riesbyfe", 12), ("Roa", 12), ("Satsuki", 12), ("Shiki Nanaya", 12), ("Shiki Ryougi", 12),
        ("Shiki Tohno", 12), ("Sion", 12), ("Sion TATARI", 12), ("Warakia", 12), ("White Len", 12),
        ("Chun-Li", 13), ("Yun", 13), ("Ken", 13), ("Makoto", 13), ("Dudley", 13), ("Urien", 13), ("Akuma", 13), ("Yang", 13), ("Ryu", 13), ("Oro", 13), ("Elena", 13),
        ("Ibuki", 13), ("Necro", 13), ("Alex", 13), ("Hugo", 13), ("Q", 13), ("Remy", 13), ("Twelve", 13), ("Sean", 13),
        ("Ryu", 14), ("Ken", 14), ("E. Honda", 14), ("Chun-Li", 14), ("Blanka", 14), ("Zangief", 14), ("Guile", 14), ("Dhalsim", 14), ("T. Hawk", 14), ("Cammy", 14), ("Fei Long", 14),
        ("Dee Jay", 14), ("Boxer / Balrog", 14), ("Claw / Vega", 14), ("Sagat", 14), ("Dictator / M. Bison", 14),
        ("O. Ryu", 14), ("O. Ken", 14), ("O. E. Honda", 14), ("O. Chun-Li", 14), ("O. Blanka", 14), ("O. Zangief", 14), ("O. Guile", 14), ("Dhalsim", 14), ("T. Hawk", 14), ("Cammy", 14), ("Fei Long", 14),
        ("O. Dee Jay", 14), ("O. Boxer / Balrog", 14), ("O. Claw / Vega", 14), ("O. Sagat", 14), ("O. Dictator / O. M. Bison", 14),
        ("Akuma", 15), ("Amateratsu", 15), ("Arthur", 15), ("Chris Redfield", 15), ("Chun-Li", 15), ("C. Viper", 15), ("Dante", 15), ("Felicia", 15), ("Firebrand", 15), ("Frank West", 15), ("Haggar", 15),
        ("Hsien-Ko", 15), ("Jill", 15), ("Morrigan", 15), ("Nemesis", 15), ("Phoenix Wright", 15), ("Ryu", 15), ("Spencer", 15), ("Strider", 15), ("Trish", 15), ("Tron Bonne", 15), ("Vergil", 15),
        ("Viewtiful Joe", 15), ("Wesker", 15), ("Zero", 15),  ("Captain America", 15), ("Deadpool", 15), ("Dormammu", 15), ("Dr. Doom", 15), ("Dr. Strange", 15), ("Ghost Rider", 15), ("Hawkeye", 15),
        ("Hulk", 15), ("Iron FIst", 15), ("Iron Man", 15), ("Magneto", 15), ("M.O.D.O.K.", 15), ("Nova", 15), ("Phoenix", 15), ("Rocket Racoon", 15), ("Sentinel", 15), ("She-Hulk", 15), ("Shuma-Gorath", 15),
        ("Spider-Man", 15), ("Storm", 15), ("Super-Skrull", 15), ("Taskmaster", 15), ("Thor", 15), ("Wolverine", 15), ("X-23", 15),
        ("Alex", 16), ("Alisa", 16), ("Ancient Ogre", 16), ("Angel", 16), ("Anna", 16), ("Armor King", 16), ("Asuka", 16), ("Baek", 16), ("Bob", 16), ("Bruce", 16), ("Bryan", 16), ("Christie", 16),
        ("Devil Jin", 16), ("Dragunov", 16), ("Eddy", 16), ("Feng", 16), ("Forest Law", 16), ("Ganryu", 16), ("Heihachi", 16), ("Hwoarang", 16), ("Jack-6", 16), ("Jaycee", 16), ("Jin", 16),("Jinpachi", 16),
        ("Jun", 16), ("Kazuya", 16), ("King", 16), ("Kuma", 16), ("Kunimitsu", 16), ("Lars", 16), ("Lee", 16), ("Lei", 16), ("Leo", 16), ("Lili", 16), ("Marduk", 16), ("Marshall Law", 16), ("Michelle", 16),
        ("Miguel", 16), ("Miharu", 16), ("Nina", 16), ("Panda", 16), ("Paul", 16), ("Prototype Jack", 16), ("Raven", 16), ("Roger Jr.", 16), ("Sebastian", 16),
        ("Slim Bob", 16), ("Steve", 16), ("Tiger Jackson", 16), ("True Ogre", 16), ("Wang", 16), ("Xiaoyu", 16), ("Yoshimitsu", 16), ("Zafina", 16);
	


INSERT INTO `user_game` (user_id, game_id) VALUES
	(1, 1);

INSERT INTO `user_character` (user_id, character_id) VALUES
        (1, 1);
