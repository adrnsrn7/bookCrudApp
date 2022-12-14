USE [master]
GO
/****** Object:  Database [library]    Script Date: 8/30/2022 8:29:36 PM ******/
CREATE DATABASE [library]
GO
ALTER DATABASE [library] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [library].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [library] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [library] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [library] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [library] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [library] SET ARITHABORT OFF 
GO
ALTER DATABASE [library] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [library] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [library] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [library] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [library] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [library] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [library] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [library] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [library] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [library] SET  DISABLE_BROKER 
GO
ALTER DATABASE [library] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [library] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [library] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [library] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [library] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [library] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [library] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [library] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [library] SET  MULTI_USER 
GO
ALTER DATABASE [library] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [library] SET DB_CHAINING OFF 
GO
ALTER DATABASE [library] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [library] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [library] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [library] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [library] SET QUERY_STORE = OFF
GO
USE [library]
GO
/****** Object:  Table [dbo].[books]    Script Date: 8/30/2022 8:29:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[books](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](50) NOT NULL,
	[description] [varchar](255) NOT NULL,
 CONSTRAINT [PK_books] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[books_categories]    Script Date: 8/30/2022 8:29:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[books_categories](
	[BookID] [int] NOT NULL,
	[CategoryID] [int] NOT NULL,
 CONSTRAINT [PK_books_categories] PRIMARY KEY CLUSTERED 
(
	[BookID] ASC,
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 8/30/2022 8:29:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](50) NOT NULL,
	[description] [varchar](255) NOT NULL,
 CONSTRAINT [PK_categories] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[books] ON 

INSERT [dbo].[books] ([id], [title], [description]) VALUES (44, N'A Tale of Two Cities', N'an 1859 historical novel by Charles Dickens, set in London and Paris before and during the French Revolution.')
INSERT [dbo].[books] ([id], [title], [description]) VALUES (45, N'The Little Prince', N'a novella by French aristocrat, writer, and military aviator Antoine de Saint-Exupéry.')
INSERT [dbo].[books] ([id], [title], [description]) VALUES (47, N'Harry Potter', N'a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley.')
INSERT [dbo].[books] ([id], [title], [description]) VALUES (48, N'And Then There Were None', N'a mystery novel by the English writer Agatha Christie, described by her as the most difficult of her books to write.')
INSERT [dbo].[books] ([id], [title], [description]) VALUES (49, N'Dream of the Red Chamber', N'a novel composed by Cao Xueqin in the middle of the 18th century. One of the Four Great Classical Novels of Chinese literature, it is known for its psychological scope, and its observation of 18th-century China')
INSERT [dbo].[books] ([id], [title], [description]) VALUES (51, N'Rosemary''s Baby', N'a 1967 horror novel by American writer Ira Levin; it was his second published book. It sold over 4 million copies, "making it the top bestselling horror novel of the 1960s."')
SET IDENTITY_INSERT [dbo].[books] OFF
GO
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (44, 13)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (44, 14)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (45, 14)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (45, 16)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (45, 20)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (47, 16)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (48, 18)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (49, 13)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (49, 19)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (51, 14)
INSERT [dbo].[books_categories] ([BookID], [CategoryID]) VALUES (51, 22)
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([id], [title], [description]) VALUES (13, N'Historical', N'a literary genre in which the plot takes place in a setting related to the past events')
INSERT [dbo].[categories] ([id], [title], [description]) VALUES (14, N'Fiction', N'created from the imagination, not presented as fact, though it may be based on a true story or situation. Types of literature in the fiction genre include the novel, short story, and novella.')
INSERT [dbo].[categories] ([id], [title], [description]) VALUES (16, N'Fantasy', N'a genre of speculative fiction involving magical elements, typically set in a fictional universe and sometimes inspired by mythology and folklore. Its roots are in oral traditions, which then became fantasy literature and drama.')
INSERT [dbo].[categories] ([id], [title], [description]) VALUES (18, N'Mystery', N'a fiction genre where the nature of an event, usually a murder or other crime, remains mysterious until the end of the story.')
INSERT [dbo].[categories] ([id], [title], [description]) VALUES (19, N'Family saga', N'a genre of literature which chronicles the lives and doings of a family or a number of related or interconnected families over a period of time.')
INSERT [dbo].[categories] ([id], [title], [description]) VALUES (20, N'Novella', N'a narrative prose fiction whose length is shorter than most novels, but longer than most short stories. The English word novella derives from the Italian novella meaning a short story related to true (or apparently so) facts.')
INSERT [dbo].[categories] ([id], [title], [description]) VALUES (22, N'Horror', N'a genre of fiction which is intended to frighten, scare, or disgust. Horror is often divided into the sub-genres of psychological horror and supernatural horror, which is in the realm of speculative fiction.')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
ALTER TABLE [dbo].[books_categories]  WITH CHECK ADD  CONSTRAINT [FK_books_categories_books] FOREIGN KEY([BookID])
REFERENCES [dbo].[books] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[books_categories] CHECK CONSTRAINT [FK_books_categories_books]
GO
ALTER TABLE [dbo].[books_categories]  WITH CHECK ADD  CONSTRAINT [FK_books_categories_categories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[categories] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[books_categories] CHECK CONSTRAINT [FK_books_categories_categories]
GO
USE [master]
GO
ALTER DATABASE [library] SET  READ_WRITE 
GO
