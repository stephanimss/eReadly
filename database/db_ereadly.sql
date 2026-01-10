--  DATABASE
CREATE DATABASE IF NOT EXISTS `db_ereadly`;
USE `db_ereadly`;


-- TABEL: books
-- Menyimpan data buku
CREATE TABLE `books` (
  `id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(100) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `stock` int DEFAULT '0',
  `rating_rata_rata` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data tabel books
INSERT INTO `books` (`id`, `title`, `author`, `category`, `stock`, `rating_rata_rata`) VALUES
(2, 'Bumi', 'Tere Liye', 'Fantasi', 8, 4.8),
(3, 'Clean Architecture', 'Robert C. Martin', 'Edukasi', 7, 4.9),
(4, 'Filosofi Teras', 'Henry Manampiring', 'Self Improvement', 1, 4.7),
(5, 'Negeri 5 Menara', 'Ahmad Fuadi', 'Religi', 8, 4.2),
(6, 'Atomic Habits', 'James Clear', 'Pengembangan Diri', 13, 0),
(7, 'The Psychology of Money', 'Morgan Housel', 'Keuangan', 18, 0),
(9, 'Selena', 'Tere Liye', 'Fiksi', 0, 0),
(11, 'Bulan', 'Tere Liye', 'Fantasi', 10, 4.7),
(12, 'Matahari', 'Tere Liye', 'Fantasi', 6, 4.6),
(14, 'Nebula', 'Tere Liye', 'Fantasi', 7, 4.6),
(16, 'Ranah 3 Warna', 'Ahmad Fuadi', 'Religi', 5, 4.1),
(17, 'Rantau 1 Muara', 'Ahmad Fuadi', 'Religi', 4, 4),
(19, 'Deep Work', 'Cal Newport', 'Pengembangan Diri', 9, 4.8),
(20, 'Digital Minimalism', 'Cal Newport', 'Pengembangan Diri', 6, 4.6),
(21, 'The 7 Habits', 'Stephen R. Covey', 'Pengembangan Diri', 11, 4.7),
(22, 'Mindset', 'Carol S. Dweck', 'Pengembangan Diri', 7, 4.6),
(24, 'Clean Code', 'Robert C. Martin', 'Edukasi', 10, 4.8),
(25, 'Refactoring', 'Martin Fowler', 'Edukasi', 6, 4.7),
(26, 'Design Patterns', 'Erich Gamma', 'Edukasi', 5, 4.6),
(27, 'Effective Java', 'Joshua Bloch', 'Edukasi', 8, 4.9),
(28, 'The Psychology of Money', 'Morgan Housel', 'Keuangan', 18, 4.7),
(29, 'Rich Dad Poor Dad', 'Robert Kiyosaki', 'Keuangan', 12, 4.5),
(30, 'The Intelligent Investor', 'Benjamin Graham', 'Keuangan', 6, 4.6),
(31, 'Think and Grow Rich', 'Napoleon Hill', 'Keuangan', 9, 4.4),
(32, 'I Will Teach You To Be Rich', 'Ramit Sethi', 'Keuangan', 5, 4.3),
(33, 'Filosofi Teras', 'Henry Manampiring', 'Self Improvement', 0, 4.7),
(34, 'Sebuah Seni Bersikap Bodo Amat', 'Mark Manson', 'Self Improvement', 14, 4.4),
(35, 'Everything is F*cked', 'Mark Manson', 'Self Improvement', 6, 4.2),
(36, 'Ikigai', 'Hector Garcia', 'Self Improvement', 10, 4.5),
(37, 'Laskar Pelangi', 'Andrea Hirata', 'Fiksi', 12, 4.8),
(38, 'Sang Pemimpi', 'Andrea Hirata', 'Fiksi', 7, 4.6),
(39, 'Edensor', 'Andrea Hirata', 'Fiksi', 5, 4.4),
(40, 'Maryamah Karpov', 'Andrea Hirata', 'Fiksi', 4, 4.3),
(41, 'Dilan 1990', 'Pidi Baiq', 'Romantis', 15, 4.1),
(42, 'Dilan 1991', 'Pidi Baiq', 'Romantis', 12, 4),
(43, 'Milea', 'Pidi Baiq', 'Romantis', 10, 4.2),
(44, 'Ayat-Ayat Cinta', 'Habiburrahman El Shirazy', 'Religi', 9, 4.3),
(45, 'Ketika Cinta Bertasbih', 'Habiburrahman El Shirazy', 'Religi', 7, 4.1),
(46, 'Bumi Cinta', 'Habiburrahman El Shirazy', 'Religi', 5, 4),
(47, 'Harry Potter 1', 'J.K. Rowling', 'Fantasi', 20, 4.9),
(48, 'Harry Potter 2', 'J.K. Rowling', 'Fantasi', 18, 4.8),
(49, 'Harry Potter 3', 'J.K. Rowling', 'Fantasi', 16, 4.9),
(50, 'Harry Potter 4', 'J.K. Rowling', 'Fantasi', 14, 4.8),
(51, 'Harry Potter 5', 'J.K. Rowling', 'Fantasi', 11, 4.7),
(52, 'The Hobbit', 'J.R.R. Tolkien', 'Fantasi', 10, 4.8),
(53, 'The Lord of the Rings 1', 'J.R.R. Tolkien', 'Fantasi', 9, 4.9),
(54, 'The Lord of the Rings 2', 'J.R.R. Tolkien', 'Fantasi', 8, 4.9),
(55, 'The Lord of the Rings 3', 'J.R.R. Tolkien', 'Fantasi', 7, 4.9),
(56, 'The Alchemist', 'Paulo Coelho', 'Fiksi', 11, 4.6),
(57, 'Brida', 'Paulo Coelho', 'Fiksi', 6, 4.3),
(58, 'Eleven Minutes', 'Paulo Coelho', 'Fiksi', 5, 4.2),
(59, 'To Kill a Mockingbird', 'Harper Lee', 'Fiksi', 7, 4.8),
(60, '1984', 'George Orwell', 'Fiksi', 8, 4.7),
(61, 'Animal Farm', 'George Orwell', 'Fiksi', 8, 4.6),
(62, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiksi', 6, 4.5),
(63, 'Sapiens', 'Yuval Noah Harari', 'Edukasi', 10, 4.9),
(64, 'Homo Deus', 'Yuval Noah Harari', 'Edukasi', 8, 4.8),
(65, '21 Lessons for the 21st Century', 'Yuval Noah Harari', 'Edukasi', 7, 4.7),
(66, 'Outliers', 'Malcolm Gladwell', 'Pengembangan Diri', 9, 4.6),
(67, 'Blink', 'Malcolm Gladwell', 'Pengembangan Diri', 6, 4.4),
(68, 'David and Goliath', 'Malcolm Gladwell', 'Pengembangan Diri', 5, 4.3),
(69, 'Start With Why', 'Simon Sinek', 'Pengembangan Diri', 8, 4.5),
(70, 'Leaders Eat Last', 'Simon Sinek', 'Pengembangan Diri', 6, 4.4),
(71, 'The Infinite Game', 'Simon Sinek', 'Pengembangan Diri', 5, 4.3),
(72, 'Perahu Kertas', 'Dewi Lestari', 'Romantis', 10, 4.4),
(73, 'Supernova', 'Dewi Lestari', 'Fiksi', 7, 4.6),
(74, 'Rectoverso', 'Dewi Lestari', 'Romantis', 6, 4.3),
(75, 'Aroma Karsa', 'Dewi Lestari', 'Fantasi', 8, 4.7),
(76, 'Cantik Itu Luka', 'Eka Kurniawan', 'Fiksi', 9, 4.6),
(77, 'Lelaki Harimau', 'Eka Kurniawan', 'Fiksi', 5, 4.4),
(78, 'Seperti Dendam, Rindu Harus Dibayar Tuntas', 'Eka Kurniawan', 'Fiksi', 6, 4.5),
(79, 'Hujan', 'Tere Liye', 'Romantis', 11, 4.5),
(80, 'Daun yang Jatuh Tak Pernah Membenci Angin', 'Tere Liye', 'Romantis', 10, 4.4),
(81, 'Rindu', 'Tere Liye', 'Romantis', 7, 4.3),
(82, 'Pulang', 'Tere Liye', 'Fiksi', 6, 4.2),
(83, 'The Subtle Art of Not Giving a F*ck', 'Mark Manson', 'Self Improvement', 12, 4.4),
(84, 'Grit', 'Angela Duckworth', 'Pengembangan Diri', 8, 4.6),
(85, 'Atomic Focus', 'Thibaut Meurisse', 'Pengembangan Diri', 6, 4.3),
(86, 'Think Like a Monk', 'Jay Shetty', 'Pengembangan Diri', 10, 4.5),
(87, 'Zero to One', 'Peter Thiel', 'Bisnis', 7, 4.4),
(88, 'The Lean Startup', 'Eric Ries', 'Bisnis', 9, 4.6),
(89, 'Good to Great', 'Jim Collins', 'Bisnis', 6, 4.5),
(90, 'Blue Ocean Strategy', 'W. Chan Kim', 'Bisnis', 5, 4.4),
(91, 'Cracking the Coding Interview', 'Gayle Laakmann', 'Edukasi', 8, 4.8),
(92, 'Introduction to Algorithms', 'Thomas H. Cormen', 'Edukasi', 4, 4.7),
(93, 'Java Programming', 'Herbert Schildt', 'Edukasi', 10, 4.5),
(94, 'Head First Java', 'Kathy Sierra', 'Edukasi', 9, 4.6),
(95, 'Database System Concepts', 'Silberschatz', 'Edukasi', 6, 4.6),
(96, 'Operating System Concepts', 'Abraham Silberschatz', 'Edukasi', 5, 4.5),
(97, 'Computer Networking', 'Andrew S. Tanenbaum', 'Edukasi', 4, 4.4),
(98, 'Dune', 'Frank Herbert', 'Sci-Fi', 7, 4.8),
(99, 'Foundation', 'Isaac Asimov', 'Sci-Fi', 6, 4.7),
(100, 'Neuromancer', 'William Gibson', 'Sci-Fi', 5, 4.6),
(101, 'Ender’s Game', 'Orson Scott Card', 'Sci-Fi', 8, 4.7),
(102, 'Sherlock Holmes', 'Arthur Conan Doyle', 'Misteri', 10, 4.8),
(103, 'The Da Vinci Code', 'Dan Brown', 'Misteri', 12, 4.5),
(104, 'Angels and Demons', 'Dan Brown', 'Misteri', 9, 4.4),
(105, 'Inferno', 'Dan Brown', 'Misteri', 8, 4.3),
(106, 'The Hunger Games', 'Suzanne Collins', 'Fantasi', 11, 4.6),
(107, 'Catching Fire', 'Suzanne Collins', 'Fantasi', 9, 4.5),
(108, 'Mockingjay', 'Suzanne Collins', 'Fantasi', 8, 4.4),
(109, 'The Maze Runner', 'James Dashner', 'Fantasi', 10, 4.4),
(110, 'The Scorch Trials', 'James Dashner', 'Fantasi', 8, 4.3),
(111, 'The Death Cure', 'James Dashner', 'Fantasi', 6, 4.2);

-- TABEL: loans
-- Data peminjaman buku
CREATE TABLE `loans` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `loan_date` date NOT NULL,
  `due_date` date NOT NULL,
  `status` enum('Borrowed','Returned') DEFAULT 'Borrowed'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data tabel loans
INSERT INTO `loans` (`id`, `user_id`, `book_id`, `loan_date`, `due_date`, `status`) VALUES
(48, 3, 2, '2026-01-01', '2026-01-08', 'Borrowed'),
(49, 3, 6, '2026-01-09', '2026-01-16', 'Borrowed'),
(50, 5, 3, '2026-01-09', '2026-01-16', 'Returned'),
(51, 5, 7, '2026-01-09', '2026-01-16', 'Borrowed'),
(53, 5, 5, '2026-01-09', '2026-01-16', 'Borrowed'),
(54, 6, 9, '2026-01-10', '2026-01-17', 'Borrowed'),
(55, 6, 2, '2026-01-10', '2026-01-17', 'Borrowed'),
(56, 6, 51, '2026-01-10', '2026-01-17', 'Borrowed'),
(57, 6, 61, '2026-01-10', '2026-01-17', 'Borrowed'),
(58, 6, 111, '2026-01-10', '2026-01-17', 'Borrowed'),
(59, 6, 109, '2026-01-10', '2026-01-17', 'Returned');

-- TABEL: notifications
-- Menyimpan notifikasi pengguna
CREATE TABLE `notifications` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `message` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data tabel notifications
INSERT INTO `notifications` (`id`, `user_id`, `message`, `created_at`, `is_read`) VALUES
(8, 3, 'Berhasil! Buku \'Bumi\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-09 18:48:30', 0),
(9, 3, 'Berhasil! Buku \'Atomic Habits\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-09 18:50:05', 0),
(10, 5, 'Berhasil! Buku \'Clean Architecture\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-09 19:03:29', 0),
(11, 5, 'Berhasil! Buku \'The Psychology of Money\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-09 19:03:33', 0),
(12, 5, 'Terima kasih! Buku \'Clean Architecture\' telah berhasil dikembalikan.', '2026-01-09 19:04:02', 0),
(13, 3, 'Berhasil! Buku \'Atomic Habits\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-09 19:05:41', 0),
(14, 5, 'Berhasil! Buku \'Negeri 5 Menara\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-09 19:07:46', 0),
(15, 6, 'Berhasil! Buku \'Selena\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-10 10:01:05', 0),
(16, 6, 'Berhasil! Buku \'Bumi\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-10 10:01:19', 0),
(17, 6, 'Berhasil! Buku \'Harry Potter 5\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-10 13:54:42', 0),
(18, 6, 'Berhasil! Buku \'Animal Farm\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-10 13:54:54', 0),
(19, 6, 'Berhasil! Buku \'The Death Cure\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-10 13:55:00', 0),
(20, 6, 'Berhasil! Buku \'The Maze Runner\' telah dipinjam. Kembalikan sebelum 17 Jan 2026.', '2026-01-10 13:55:10', 0),
(21, 6, 'Terima kasih! Buku \'The Maze Runner\' telah berhasil dikembalikan.', '2026-01-10 14:03:51', 0);

-- TABEL: ratings
-- Menyimpan penilaian buku
CREATE TABLE `ratings` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `score` int DEFAULT NULL,
  `comment` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data tabel ratings
INSERT INTO `ratings` (`id`, `user_id`, `book_id`, `score`, `comment`, `created_at`) VALUES
(4, 3, 2, 3, 'Bukunya cukup menarik dan seru', '2026-01-09 19:05:32'),
(5, 3, 6, 5, 'Buku yang sangat bermanfaat', '2026-01-09 19:06:05'),
(6, 5, 2, 4, 'Alur ceritanya wow banget', '2026-01-09 19:07:02'),
(7, 5, 5, 2, 'Kurang greget alur nya', '2026-01-09 19:07:24'),
(22, 3, 2, 5, 'Bukunya seru banget dan alurnya rapi', '2026-01-10 13:53:16'),
(23, 3, 3, 4, 'Ceritanya menarik tapi agak lambat di tengah', '2026-01-10 13:53:16'),
(24, 3, 4, 5, 'Sangat memotivasi dan mudah dipahami', '2026-01-10 13:53:16'),
(25, 5, 2, 5, 'Fantasinya keren dan bikin nagih', '2026-01-10 13:53:16'),
(26, 5, 3, 4, 'Bahasannya dalam dan relevan', '2026-01-10 13:53:16'),
(27, 5, 5, 2, 'Kurang greget menurut saya', '2026-01-10 13:53:16'),
(28, 5, 6, 5, 'Buku ini wajib dibaca', '2026-01-10 13:53:16'),
(29, 6, 3, 3, 'Lumayan bagus tapi tidak spesial', '2026-01-10 13:53:16'),
(30, 6, 4, 5, 'Alur ceritanya wow banget', '2026-01-10 13:53:16'),
(31, 6, 5, 4, 'Cocok dibaca santai', '2026-01-10 13:53:16');

-- TABEL: users
-- Menyimpan data pengguna
CREATE TABLE `users` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MEMBER') NOT NULL DEFAULT 'MEMBER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data tabel users
INSERT INTO `users` (`id`, `nama`, `email`, `password`, `role`) VALUES
(3, 'Stephani', 'stephani123@gmail.com', 'b1621832baa6310cf5d17669d917d6ec5eba3ec8ec927c1678b5659974b0b0f6', 'MEMBER'),
(4, 'Bunga', 'bunga123@gmail.com', '5eb8283b029a7e8587e7807856fcfad04e3bd1fee293d781c8b9f0b003b6d8ca', 'ADMIN'),
(5, 'Reggie', 'reggie@gmail.com', '1662ca223dc35a9bf599af2fda09915dad0da1a20cbf5f706a4f09f3a95646db', 'MEMBER'),
(6, 'maknun', 'maknun123@gmail.com', 'bb42e80dc29518889671f1b39a9a1a3aa1a8fb3774ef3b1a7de506ef46365109', 'MEMBER'),
(8, 'adminlu', 'adminlu@admin.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'ADMIN');

--  PRIMARY KEY & INDEX
ALTER TABLE `books` ADD PRIMARY KEY (`id`);
ALTER TABLE `loans` ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`), ADD KEY `book_id` (`book_id`);
ALTER TABLE `notifications` ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`);
ALTER TABLE `ratings` ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`user_id`), ADD KEY `book_id` (`book_id`);
ALTER TABLE `users` ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--  AUTO_INCREMENT
ALTER TABLE `books` MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;
ALTER TABLE `loans` MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
ALTER TABLE `notifications` MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
ALTER TABLE `ratings` MODIFY `id` int NOT NULL AUTO_INCREMENT;
ALTER TABLE `users` MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--  FOREIGN KEY
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE;

ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `ratings`
  ADD CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE;

COMMIT;
