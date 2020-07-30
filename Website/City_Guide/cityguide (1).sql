-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2020 at 01:16 PM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cityguide`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `AdminId` int(11) NOT NULL,
  `AdminName` varchar(20) NOT NULL,
  `AdminEmail` varchar(70) NOT NULL,
  `AdminPassword` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminId`, `AdminName`, `AdminEmail`, `AdminPassword`) VALUES
(2, 'Divy', 'chavdadivy29@gmail.com', 'Divy@123');

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE IF NOT EXISTS `advertisement` (
  `AdvertisementId` int(11) NOT NULL,
  `AdvertisementImage` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `advertisement`
--

INSERT INTO `advertisement` (`AdvertisementId`, `AdvertisementImage`) VALUES
(1, '1.png'),
(2, '2.jpeg'),
(3, '3.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `business`
--

CREATE TABLE IF NOT EXISTS `business` (
  `BusinessId` int(11) NOT NULL,
  `BusinessUserIdFK` int(11) NOT NULL,
  `BusinessCategoryIdFK` int(11) NOT NULL,
  `CityIdFK` int(11) NOT NULL,
  `BusinessTitle` varchar(50) NOT NULL,
  `BusinessAddress` text NOT NULL,
  `BusinessFacility` varchar(50) NOT NULL,
  `BusinessDescription` text NOT NULL,
  `BusinessPhoneNo` varchar(20) NOT NULL,
  `BusinessLandlineNo` varchar(20) NOT NULL,
  `BusinessWebsite` varchar(30) NOT NULL,
  `BusinessProfilePicture` varchar(100) NOT NULL,
  `BusinessLatitude` varchar(20) NOT NULL,
  `BusinessLongitude` varchar(20) NOT NULL,
  `BusinessStatus` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `business`
--

INSERT INTO `business` (`BusinessId`, `BusinessUserIdFK`, `BusinessCategoryIdFK`, `CityIdFK`, `BusinessTitle`, `BusinessAddress`, `BusinessFacility`, `BusinessDescription`, `BusinessPhoneNo`, `BusinessLandlineNo`, `BusinessWebsite`, `BusinessProfilePicture`, `BusinessLatitude`, `BusinessLongitude`, `BusinessStatus`) VALUES
(23, 5, 10, 5, 'Hotel Girnar', 'National Highway No 8, Kamrej Char Rasta, Surat - 394185, Amboli Near Irb Toll Plaza                                        											', 'Pure Veg Restaurants', 'Making way for a hearty meal is Hotel Girnar in Surat. Established in the year 1982, this place is synonymous with delicious food that can satiate all food cravings. It is home to some of the most appreciated cuisines which include North Indian,Kathiyawadi,Gujarati,Punjabi,Pure Veg,Indian,Jain. So as to be able to cater to a large number of diners, it occupies a favourable location at Kamrej Char Rasta. Amboli Near Irb Toll Plaza,National Highway No 8,Kamrej Char Rasta-394185 is where one can visit the venue. Courtesy to this strategic location, foodies in and around the neighborhood can walk in to this eating house conveniently without facing any hassles related to commuting to this part of the city. It is one of the most sought after Kathiyawadi Restaurants in Kamrej Char Rasta. This is a one of the renowned Kathiyawadi Restaurants in Surat.\r\nHotel Girnar at Kamrej Char Rasta makes sure one has a great food experience by offering highly palatable food. The restaurant welcomes guests from 10:00-18:00 - 15:30-23:00 allowing diners to relish a scrumptious meal between the functional hours.                                   \r\n																																	', '9152583214', '-', 'www.hotelgirnar.com', 'girnar-3.jpg', '21.284850', '72.972400', 'Approve'),
(24, 10, 15, 5, 'Tulsi Restaurant', 'Block No 132/133, Ground Floor, Utsav, Nana Varaccha Road, Nana Varaccha, Surat - 395006, Opposite Maharana Pratap Garden, Chopati                                         											', 'Food', 'Making way for a hearty meal is Hotel Tulsi in Surat. Established in the year 2015, this place is synonymous with delicious food that can satiate all food cravings. It is home to some of the most appreciated cuisines. So as to be able to cater to a large number of diners, it occupies a favourable location at Nana Varaccha. Opposite Maharana Pratap Garden, Chopati,Block No 132/133, Ground Floor, Utsav,Nana Varaccha Road,Nana Varaccha-395006 is where one can visit the venue. Courtesy to this strategic location, foodies in and around the neighborhood can walk in to this eating house conveniently without facing any hassles related to commuting to this part of the city. It is one of the most sought after Restaurants in Nana Varaccha. For people in other parts, the restaurant also has branches at other locations which include Borsara Village - Kim and can also be visited for an appetizing meal. This is a one of the renowned Restaurants in Surat.\r\nHotel Tulsi at Nana Varaccha makes sure one has a great food experience by offering highly palatable food. The various services offered at the venue include Pure Vegetarian , Take Away . The restaurant welcomes guests from 10:30-18:00 - 15:30-23:00 allowing diners to relish a scrumptious meal between the functional hours.                                        \r\n																						', '9099334757', '0', 'www.tulsirestaurant.com', 'tulsi.jpg', '21.196431', '72.847159', 'Approve'),
(25, 5, 10, 5, 'The Grand Bhagwati', 'Dumas Road, Surat - 395007, Magdalla Circle, Near City Pulse                                            \r\n											', 'Food,Room,Meeting Hall', 'The Grand Bhagwati in Surat, India, is the only five-star hotel in Surat and is set off NH6. The hotel is around three miles from Lake View Garden.\r\nThe rooms and suites at the hotel are equipped with flat screen satellite TVs, minibars, safes, desks and coffeemakers. Bathrooms have shower and tub combination. Suites also have separate living areas and upgraded units have whirlpool tubs.\r\nThe hotel has an outdoor pool, sauna and spa tub. A fitness room, game room, pool table and tennis court plus business centre are present onsite. Airport transport and Wi-Fi are available for a surcharge. Valet parking and self parking onsite is complimentary.\r\nThe Grand Bhagawati has five restaurants, a bakery and nightclub onsite. The Ziba Restaurant serves Indian meals and the Cafe Piano serves an international menu. The Club Cafe serves drinks and coffee.\r\nThe hotel is around eight miles from Merulaxmi Temple and around nine miles from Dutch Garden."                                            \r\n											', '8980001054', '0', 'www.tgbhotels.com', 'tjb.jpg', '21.194095', '72.786623', 'Approve'),
(26, 5, 10, 5, 'Hotel Suncity', 'Unapani Road,Opposite Amisha Hotel ,Near Railway Station,LAL Darwaja,Surat-395003                                            \r\n											', 'Food,Rooms', 'Having been established in the year 2011, this hospitality destination has grown to become the ideal place for travelers and those on business to feel at home when in the city. Many have also reviewed the hotel to be among the sought after Hotels in Surat. The hotel functions from 00:00 - 23:59 all through the week. Catering to the convenience of its guests, the hotel makes available various payment methods to ease out the payment process like, Cash, Master Card, Visa Card, Debit Cards, Cheques, Credit Card, Travelers Cheque, American Express Card.\r\nWith the aim of pampering its guests to a thrilling experience, the hotel features a plethora of services which include Number Of Rooms 24, Internet Access , Laundry Service , Air Conditioned Rooms , Cable/satellite Tv Service , In room Safe(locker) , Banquet Facilities , Video Conferencing , Wifi Service/wireless Internet , Wired Internet Facility , Conference Room(s) , Convention Centre , Check Out Time 12:00, Distance In (kms) From The Airport 15km, 24 Hour Concierge/help Desk yes. The hotel is also classified as a destination to resort to when in need of Hotels, Hotels (Rs 1001 To Rs 2000), 3 Star Hotels.                                             ', '9375290619', '0', 'www.hotelsuncitysurat.com', 'suncity-1.jpg', '21.196102', '72.815766', 'Approve'),
(27, 10, 15, 5, 'Navjivan Restaurant', 'Bhurakhiya Arcade, Surat to Kamrej Road, Varachha Road, Surat - 395006, Opposite Everest Gas Point, Near Sarthana Jakatnaka                                            \r\n											', 'Pure Vegetarian ,Indian Restaurants', 'Navjivan Restaurant at Varachha Road makes sure one has a great food experience by offering highly palatable food. The various services offered at the venue include Ac , Pure Vegetarian , Dine in , Home Delivery , Jain Friendly , Price For Two 400 . The restaurant welcomes guests from 11:30 - 23:00 allowing diners to relish a scrumptious meal between the functional hours.\r\nAlso Serve Chinese Cuisine, South Indian Cuisine, Fast Food.', '9924938014', '0', 'www.navjivanrestaurant.com', 'navjivan-1.jpg', '36.552613', '4.100473', 'Approve'),
(28, 10, 15, 5, ' Kabir Restaurant', 'National Plaza, Near Ayurvedic Hospital, Opposite Railway Station, Surat, Gujarat 395003                                            \r\n											', 'Food', 'Punjabi Restaurants Pure Veg Restaurants Multicuisine Restaurants Indian Restaurants North Indian Restaurants                                            \r\n											', '9199561625', '0', 'www.kabirrestaurant.com', 'kabir-1.jpg', '21.196102', '72.815766', 'Approve'),
(29, 11, 13, 5, 'STBS College of Diploma Engineering', 'Opp. kapodra police station , Varachha Road, Kapodra, SURAT â€“ 395 006.', 'Seminar Hall and Conference Room, Auditorium, Inte', 'Shree Tapi Brahamcharyashram Sabha College of Diploma Engineering was established in January -2002, by Shree Tapi Brahmcharyashram Sabha Trust. The College is affiliated to Gujarat Technological University (GTU) approved by AICTE (All India Council for Technical Education)\r\n\r\nShree Tapi Brahamcharyashram Sabha trust was rooted in 1924 by Shree Swami Atmanand Saraswati, a great social reformer and educationalist.  The members of the trust are people with deep interest and commitment to develop state-of-the-art Infrastructure for educational pursuit.  Our visionary trustees have been toiling hard to give these institution splendid physical and cultural dimensions.\r\n\r\nFrom the beginning, we have created benchmarks on the engineering education community of Gujarat. To work with innovative idea in technology, to solve the real life problems and to face the changes and challenges of this new age, our  institute is the best place for students seeking a solid technological and scientific foundation and personal interaction with the eminent teachers imparting quality teaching in various disciplines.\r\n\r\nSTBS College of diploma Engineering emphasizes to impart quality education to the students and thereby make dynamic professional in their respective fields and worthy citizens of great country.\r\n\r\nRising prestige of STBS College is not only to provide higher degree of academic excellence but to present potential youth in the world of engineering by means of extracting their talent.  				', '9126125716717992', '0', 'www.stbscollege.org', 'stbs-1.jpg', '21.196102', '72.815766', 'Approve'),
(30, 11, 13, 5, 'SSASIT College of Degree Engineering', 'Opp. kapodra police station , Varachha Road, Kapodra, SURAT â€“ 395 006                                            \r\n											', 'Seminar Hall and Conference Room, Auditorium, Inte', 'SSASIT College of Degree Engineering emphasizes to impart quality education to the students and there by making dynamic professional in their respective fields and worthy citizens of great country. Rising prestige of SSASIT College is not only to provide higher degree of academic excellence but to present potential youth in the world of engineering by means of extracting their talent.                                            \r\n											', '02612573552', '0', 'www.ssasit.ac.in', 'ssasit-1.jpg', '21.196102', '72.815766', 'Approve'),
(31, 12, 14, 5, 'SMIMER Hospital & Medical College', 'Bombay Market Rd, Sahara Darwaja, Near, Umarwada, Surat, Gujarat 395010                                            \r\n											', 'Open 24 hours,College', 'Surat Municipal Institute of Medical Education and Research (SMIMER) is a medical college and teaching hospital in Surat, Gujarat, India, which is the fourth-fastest-growing city in the world.[1] It was established in 2000 and has been affiliated to the Veer Narmad South Gujarat University. The institute is spread over a floor area of 18,427 square metres (198,350 sq ft) for the medical college, and 75,217 square metres (809,630 sq ft) for the hospital section. It was developed with a total expenditure of Rs. 484.29 million by the Surat Municipal Corporation. [2]\r\n\r\nThe medical college admits 150 undergraduate students each year. The college campus has an area of 25 acres (100,000 m2). SMIMER is owned and run by the Surat Municipal Corporation and is approved by the Medical Council of India under MCI Act 10 A.\r\n\r\nThe college has its own hospital located next to the medical college complex with 500 beds, out of which 90% are free beds and 10% are paying beds in the special wards. Dr. Vandana Desai is the Administrative Medical Superintendent.[3] The casualty department i.e. emergency medicine or trauma center is available 24 hours a day and an average of 100â€“125 patients are treated every day. The hospital is a non-profit organisation run by Surat Municipal Corporation.                                            \r\n											', '02612638041', '0', 'www.smimer.suratmunicipal.gov.', 'simimer-1.jpg', '21.196102', '72.815766', 'Approve'),
(32, 12, 14, 5, 'KiranHospital', 'Kiran Multi Super Speciality Hospital & Research Center,Nr Sumul Dairy, Surat - 395004                                            \r\n											', 'Floor plan,Patient Room,General Guide,internationa', 'Kiran Hospital is now one of the first hospitals from India to get appreciated for World Class Healthcare services in field of Bariatric Surgery for Weight Loss by Bariatric Times\r\nBariatric Times, internationally acclaimed journal from Pennsylvania, USA on bariatric trends, research, developments utilized in the treatment of obesity and metabolic disorders.\r\nCongratulations to whole Bariatric team of Doctors, colleagues and Healthcare Support System Kiran Hospital,\r\nSpecial Thanks To Our Trustee Vallabhbhai Lakhani Kiran Gems Private Limited Kiran Exports Kiran Gems For Kind Support Kiran Multi Super Speciality Hospital & Research Centre.                                            \r\n											', '912617161111', '0', 'www.kiranhospital.com', 'kiran-1.png', '50.340645', '106.709686', 'Approve'),
(33, 13, 16, 5, 'Bank Of Baroda', '49, Katargam Rd, Tunki, Katargam, Surat, Gujarat 395004                                            \r\n											', 'open 24 hours', 'Bank Of Baroda in Surat holds a commodious office with modern organizational infrastructure. Managed by a professional team, the bank lends complete support to its clients in various matters like opening an account, transferring money, getting loans approved and plenty more. It ensures to carry out everything legally which makes it a reliable organization and helps it to gain the trust and confidence of its customers and clients. For any kind of services, the bank stringently follows the rules and regulations as is designated by the Government of India, checks and cross checks all significant documents and meets any other formalities as is required, all within the constitutional frame. The internet banking facility proves extremely favourable for the account holders of this bank enabling them to transact anytime and from anywhere. It operates from 00:00 - 23:59 between Monday-Friday and remains closed on every second and fourth Saturdays of the month. To know further, customers can call up at +(91)-261-2480258.                                            \r\n											', '912612480258', '0', 'www.bankofbaroda.com', 'bob-1.jpg', '17.405816', '78.493253', 'Approve'),
(34, 14, 17, 5, 'Bank Of Baroda Katargam', 'Opp Katargam Police Station, Katargam Main Road, Surat, Gujarat 395004.                                            \r\n											', 'saving account, Loan, Fixd Deposits,Credit Card', 'Bank of Baroda is the second largest Indian bank owned by the state founded on 20th July 1908 and is head-quartered in Vadodara, Gujarat, India. It offers banking products to meet the banking needs of individuals. It has a global network of a total 5326 bank-branches and over 8000 ATMs. It has been classified as a profit-making public sector institution.                                            \r\n											', '18001024455', '0', 'www.bankofbaroda.com', 'bob-1.jpg', '12.989725', '74.800781', 'Approve'),
(35, 15, 18, 5, 'Rampura Petrol Pump', '93, Surat Main Road, Rampura Main Road, Rampura, Surat - 395003                                            \r\n											', 'Open 24 Hrs', 'Best Service,Best Quality                                            \r\n											', '9998455978', '0', 'www.rampurapetrolpump.com', 'bharat-1.jpg', '33.628494', '73.101145', 'Approve'),
(36, 15, 18, 5, 'Bharat Petrol Pump', 'CHOWK BAZAR, SURAT, Surat, Gujarat 395001                                            \r\n											', 'Open 24 Hrs', 'www.bharatpetrolpump.com                                            \r\n											', '02612424058', '0', 'www.bharatpetrolpump.com', 'ram-1.jpg', '21.417430', '72.965174', 'Approve'),
(37, 16, 19, 5, 'P.P.Savani', 'Mota Varachcha â€“ Abrahma Road, At & Po. Abrama Tah. â€“ Kamrej, Surat 394150                                            \r\n											', 'Best Teaching', 'We always believe in our motto, â€œNurturing your childâ€™s individualityâ€, and so we want each student to fulfil their potential and make the most of their strengths and interests. Cambridge International Examinations, offer a wide range of subjects and let every student choose the subjects they love and the subjects theyâ€™re best at. CIE design programmes â€“ with the help of expert educators in schools and universities â€“ to challenge students, and get them excited about what theyâ€™re learning. They learn about the key concepts of each subject in depth, so they understand them inside and out. Our programmes are flexible, so teachers can use examples that are relevant to studentsâ€™ local context and culture. Students learn in English, and Cambridge assessment is accessible to speakers of English as a second or foreign language. Our alumni speak, â€œWe really enjoyed our studies in the P. P. Savani Cambridge International School. We were seeking an international platform, and an international curriculum which can help us in the global studies. Thanks to the CIE curriculum which helped us getting admissions in desired universities and also made further studies easy as most of the topics were already taught in the school. This gave us an extra advantage over the other students from other boardsâ€.                                            \r\n											', '919909991024', '0', 'www.ppsu.ac.in', 'pp-1.jpg', '10.963901', ' 74.816728', 'Approve'),
(38, 16, 19, 5, 'Shree Swaminarayan Gurukul Vidyalaya ', 'Shree Swaminarayan Gurukul, Gurukul Road (Ved), Surat-395004                                             \r\n											', 'Best Teaching', 'Shree Swaminarayan gurukul is founded to furnish students of india with culture, knowledge, amd manners. Gurukul is also building up the best characters of students through real education. It is creating secular harmony among all the religious. Gurukul is balancing between spirituality and modernity. Also promoting and propagating the message of Bhagavan Swamianarayan. It is serving society with medical, education and cultural activities.                                            \r\n											', '919099511000', '0', 'gurukulsurat.org', 's-1.jpg', '40.931449', '74.055738', 'Approve'),
(39, 17, 20, 5, 'BIGBAZAAR', 'Kamdhenu Mall, Dumas Road, Surat - 395007, Opposite Gujarat Gas Cng Station                                            \r\n											', 'All Type of Food,Jewellery,ect', 'Established in the year 2006, Big Bazaar in Dumas Road, Surat is a top player in the category Supermarkets in the Surat. This well-known establishment acts as a one-stop destination servicing customers both local and from other parts of Surat. Over the course of its journey, this business has established a firm foothold in itâ€™s industry. The belief that customer satisfaction is as important as their products and services, have helped this establishment garner a vast base of customers, which continues to grow by the day. This business employs individuals that are dedicated towards their respective roles and put in a lot of effort to achieve the common vision and larger goals of the company. In the near future, this business aims to expand its line of products and services and cater to a larger client base. In Surat, this establishment occupies a prominent location in Dumas Road. It is an effortless task in commuting to this establishment as there are various modes of transport readily available. It is at , Opposite Gujarat Gas Cng Station, which makes it easy for first-time visitors in locating this establishment. The popularity of this business is evident from the 2100+ reviews it has received from Justdial users. It is known to provide top service in the following categories: Supermarkets, Mobile Phone Dealers, Electronic Goods Showrooms, Jewellery Showrooms, Furniture Dealers, AC Dealers, Mobile Phone Dealers-Samsung, Readymade Garment Retailers.                                            \r\n											', '18002002255', '0', 'www.bigbazaar.com', 'big-1.jpg', '15.202963', '73.990248', 'Approve'),
(40, 17, 20, 5, 'VR Mall', 'VR Surat, Dumas Rd, Magdalla,Surat, Gujarat 395007                                            \r\n											', 'All Type of Food,Jewellery,Movies,ect.', 'VR Surat invites you all to come experience a place inspired by the idea of Surat, inspired by the communities that make it unique, inspired by the heritage that gives it character and inspired by the people of Surat ', '912616785001', '0', 'www.vrsurat.com', 'vr-1.jpg', '21.196102', '72.815766', 'Approve');

-- --------------------------------------------------------

--
-- Table structure for table `business_images`
--

CREATE TABLE IF NOT EXISTS `business_images` (
  `BusinessImageId` int(11) NOT NULL,
  `BusinessIdFK` int(11) NOT NULL,
  `BusinessImages` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `business_images`
--

INSERT INTO `business_images` (`BusinessImageId`, `BusinessIdFK`, `BusinessImages`) VALUES
(5, 23, 'girnar-1.jpg'),
(6, 23, 'girnar-2.jpg'),
(7, 23, 'girnar-4.jpg'),
(8, 25, 'tjb1.jpg'),
(9, 25, 'tjb2.jpg'),
(10, 25, 'tjb3.jpg'),
(11, 26, 'suncity-2.jpg'),
(12, 26, 'suncity-3.jpg'),
(13, 26, 'suncity-4.jpg'),
(14, 24, 'tulsi-2.jpg'),
(15, 24, 'tulsi-3.jpg'),
(16, 24, 'tulsi-4.jpg'),
(17, 27, 'navjivan-2.jpg'),
(18, 27, 'navjivan-3.jpg'),
(19, 27, 'suncity-4.jpg'),
(20, 28, 'kabir-2.jpg'),
(21, 28, 'kabir-3.jpg'),
(22, 28, 'kabir-4.jpg'),
(23, 29, 'stbs-2.jpg'),
(24, 29, 'stbs-3.jpg'),
(25, 29, 'stbs-4.jpg'),
(26, 30, 'ssasit-2.jpg'),
(27, 30, 'ssasit-3.jpg'),
(28, 30, 'ssasit-4.jpg'),
(29, 31, 'simimer-2.jpg'),
(30, 31, 'simimer-3.jpg'),
(31, 31, 'simimer-4.jpg'),
(32, 32, 'kiran-2.png'),
(33, 32, 'kiran-3.png'),
(34, 32, 'kiran-4.png'),
(35, 33, 'bob-2.jpg'),
(36, 33, 'bob-3.jpg'),
(37, 33, 'bob-4.jpg'),
(38, 35, 'ram-1.jpg'),
(39, 35, 'ram-2.jpg'),
(40, 35, 'ram-3.jpg'),
(41, 36, 'bharat-2.jpg'),
(42, 36, 'bharat-3.jpg'),
(43, 36, 'bharat-4.jpg'),
(44, 37, 'pp-2.jpg'),
(45, 37, 'pp-3.jpg'),
(46, 37, 'pp-4.jpg'),
(47, 38, 's-2.jpg'),
(48, 38, 's-3.jpg'),
(49, 38, 's-4.jpg'),
(50, 39, 'big-2.jpg'),
(51, 39, 'big-3.jpg'),
(52, 39, 'big-4.jpg'),
(53, 40, 'vr-2.jpg'),
(54, 40, 'vr-3.jpg'),
(55, 40, 'vr-4.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `business_user`
--

CREATE TABLE IF NOT EXISTS `business_user` (
  `BusinessUserId` int(11) NOT NULL,
  `BusinessUserName` varchar(50) NOT NULL,
  `BusinessUserEmail` varchar(50) NOT NULL,
  `BusinessUserMobile` varchar(20) NOT NULL,
  `BusinessUserPassword` varchar(30) NOT NULL,
  `BusinessUserIcon` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `business_user`
--

INSERT INTO `business_user` (`BusinessUserId`, `BusinessUserName`, `BusinessUserEmail`, `BusinessUserMobile`, `BusinessUserPassword`, `BusinessUserIcon`) VALUES
(5, 'abc', 'abc@gmail.com', '2147483647', 'abc123', 'IMG-20160925-WA0005.jpg'),
(10, 'Dev', 'dev1213@gmail.com', '2147483647', 'dev123', 'heart1.png'),
(11, 'Divy', 'chavdadivy29@gmail.com', '9874632145', 'divy123', 'college.jpg'),
(12, 'Hospital', 'hospital@gmail.com', '7048155220', 'Hospital@123', 'hospital.png'),
(13, 'ATM', 'atm@gmail.com', '7048155220', 'Atm@1234', 'sbi-3.jpg'),
(14, 'bank', 'bank@gmail.com', '7048155220', 'Bank@123', 'sbi-1.jpg'),
(15, 'petrol', 'petrol@gmailcom', '7048155220', 'Petrol@123', 'ram-2.jpg'),
(16, 'school', 'school@gmail.com', '7048155220', 'School@123', 'orbit-1.png'),
(17, 'shoping', 'shoping@gmail.com', '7048155220', 'Shoping@123', 'big-2.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `CategoryId` int(11) NOT NULL,
  `CategoryName` varchar(20) NOT NULL,
  `CategoryIcon` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`, `CategoryIcon`) VALUES
(10, 'Hotel ', 'hotel.png'),
(13, 'college', 'college.jpg'),
(14, 'Hospital', 'hospital.png'),
(15, 'Restaurant', 'restaurants.png'),
(16, 'ATM Machine', 'atm.png'),
(17, 'Bank', 'bank.png'),
(18, 'Petrol Pump', 'petrol.png'),
(19, 'School', 'school.png'),
(20, 'Shopping', 'shopping.png');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `CityId` int(11) NOT NULL,
  `CityName` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`CityId`, `CityName`) VALUES
(5, 'Surat'),
(6, 'vadodara');

-- --------------------------------------------------------

--
-- Table structure for table `favourite`
--

CREATE TABLE IF NOT EXISTS `favourite` (
  `Id` int(11) NOT NULL,
  `BussinessIdFK` int(11) NOT NULL,
  `BusinessUserIdFK` int(11) NOT NULL,
  `Favourite` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `favourite`
--

INSERT INTO `favourite` (`Id`, `BussinessIdFK`, `BusinessUserIdFK`, `Favourite`) VALUES
(1, 23, 11, 1),
(4, 23, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `UserEmail` varchar(50) NOT NULL,
  `UserNumber` varchar(20) NOT NULL,
  `UserReview` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`Id`, `UserName`, `UserEmail`, `UserNumber`, `UserReview`) VALUES
(1, 'Divy', 'chavdadivy29@gmail.com', '7048155220', 'bestapp'),
(2, 'Divy', 'chavdadivy29@gmail.com', '7048155220', 'best app'),
(3, 'ff', 'gfd@ghdd.com', '55888', 'ddv'),
(4, 'fff', 'ffdt@ggg.com', '85508', 'ddrg'),
(5, 'bababa', 'hagg@gmail.com', '546494', 'vagatatfaa gagag'),
(6, 'saw', 'saw@df.com', '4885', 'aSa');

-- --------------------------------------------------------

--
-- Table structure for table `forgotpassword`
--

CREATE TABLE IF NOT EXISTS `forgotpassword` (
  `Id` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rating_review`
--

CREATE TABLE IF NOT EXISTS `rating_review` (
  `Id` int(11) NOT NULL,
  `BusinessIdFK` int(11) NOT NULL,
  `BusinessUserIdFK` int(11) NOT NULL,
  `Rating` varchar(10) NOT NULL,
  `Review` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rating_review`
--

INSERT INTO `rating_review` (`Id`, `BusinessIdFK`, `BusinessUserIdFK`, `Rating`, `Review`) VALUES
(1, 23, 5, '5.0', 'good'),
(2, 24, 5, '5.0', 'best restaurant'),
(3, 24, 5, '5.0', 'super'),
(4, 25, 5, '5.0', 'very gud food with cheap rates'),
(5, 27, 5, '4.5', 'best restaurant'),
(6, 27, 5, '4.5', 'best service'),
(7, 27, 5, '4.5', 'best'),
(8, 23, 5, '4.5', 'best hotal'),
(9, 23, 5, '4.5', 'nice');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `UserEmail` varchar(50) NOT NULL,
  `UserPassword` varchar(50) NOT NULL,
  `UserMobileNumber` varchar(20) NOT NULL,
  `UserGender` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`UserId`, `UserName`, `UserEmail`, `UserPassword`, `UserMobileNumber`, `UserGender`) VALUES
(5, 'Divy', 'chavdadivy29@gmail.com', 'Divy@123', '7048155220', 'Male'),
(6, 'di', 'Di@123', 'asdfghj', '9033440035', 'male'),
(7, 'Divy', 'Divy1213@gmail.com', 'Dev@123', '7048155220', 'Female'),
(8, 'Divu', 'divu@gmail.com', 'divu123', '12346737363', 'Male'),
(9, 'gsgsg', 'bzb@gmail.com', 'hshha', '754543494', 'Male'),
(10, 'hshsba', 'bahab@gmail.com', 'hahahH@12', '94645454', 'Male'),
(11, 'Test', 'test@gmail.com', 'Test@123', '7048155220', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`AdminId`);

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`AdvertisementId`);

--
-- Indexes for table `business`
--
ALTER TABLE `business`
  ADD PRIMARY KEY (`BusinessId`),
  ADD KEY `BusinessUserIdFK` (`BusinessUserIdFK`),
  ADD KEY `BusinessCategoryIdFK` (`BusinessCategoryIdFK`),
  ADD KEY `BusinessCategoryIdFK_2` (`BusinessCategoryIdFK`),
  ADD KEY `BusinessCategoryIdFK_3` (`BusinessCategoryIdFK`),
  ADD KEY `CityIdFK` (`CityIdFK`);

--
-- Indexes for table `business_images`
--
ALTER TABLE `business_images`
  ADD PRIMARY KEY (`BusinessImageId`),
  ADD KEY `BussinessIdFK` (`BusinessIdFK`),
  ADD KEY `BusinessIdFK` (`BusinessIdFK`);

--
-- Indexes for table `business_user`
--
ALTER TABLE `business_user`
  ADD PRIMARY KEY (`BusinessUserId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`CityId`);

--
-- Indexes for table `favourite`
--
ALTER TABLE `favourite`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `BussinessIdFK` (`BussinessIdFK`),
  ADD KEY `BusinessUserIdFK` (`BusinessUserIdFK`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `forgotpassword`
--
ALTER TABLE `forgotpassword`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `rating_review`
--
ALTER TABLE `rating_review`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `BusinessIdFK` (`BusinessIdFK`),
  ADD KEY `BusinessUserIdFK` (`BusinessUserIdFK`),
  ADD KEY `BusinessUserIdFK_2` (`BusinessUserIdFK`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`UserId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `AdminId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `advertisement`
--
ALTER TABLE `advertisement`
  MODIFY `AdvertisementId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `business`
--
ALTER TABLE `business`
  MODIFY `BusinessId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `business_images`
--
ALTER TABLE `business_images`
  MODIFY `BusinessImageId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `business_user`
--
ALTER TABLE `business_user`
  MODIFY `BusinessUserId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CategoryId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `CityId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `favourite`
--
ALTER TABLE `favourite`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `forgotpassword`
--
ALTER TABLE `forgotpassword`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rating_review`
--
ALTER TABLE `rating_review`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `business`
--
ALTER TABLE `business`
  ADD CONSTRAINT `business_ibfk_1` FOREIGN KEY (`BusinessUserIdFK`) REFERENCES `business_user` (`BusinessUserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `business_ibfk_2` FOREIGN KEY (`BusinessCategoryIdFK`) REFERENCES `category` (`CategoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `business_ibfk_3` FOREIGN KEY (`CityIdFK`) REFERENCES `city` (`CityId`);

--
-- Constraints for table `business_images`
--
ALTER TABLE `business_images`
  ADD CONSTRAINT `business_images_ibfk_1` FOREIGN KEY (`BusinessIdFK`) REFERENCES `business` (`BusinessId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `favourite`
--
ALTER TABLE `favourite`
  ADD CONSTRAINT `favourite_ibfk_1` FOREIGN KEY (`BussinessIdFK`) REFERENCES `business` (`BusinessId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favourite_ibfk_2` FOREIGN KEY (`BusinessUserIdFK`) REFERENCES `registration` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rating_review`
--
ALTER TABLE `rating_review`
  ADD CONSTRAINT `BusinessIdFK` FOREIGN KEY (`BusinessIdFK`) REFERENCES `business` (`BusinessId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rating_review_ibfk_1` FOREIGN KEY (`BusinessUserIdFK`) REFERENCES `registration` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
