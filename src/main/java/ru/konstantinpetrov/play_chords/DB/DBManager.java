package ru.konstantinpetrov.play_chords.DB;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import ru.konstantinpetrov.play_chords.entity.*;


public class DBManager {
	private static final String URL="jdbc:postgresql://pg:5432/studs";
	private static final String USERNAME="s339742";
	private static final String PASSWORD="eMgmoDoZhCcsWa62";
	
	private static Connection connection;
	
	static {
		try {
			Class.forName("org.postgresql.Driver"); 
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection=DriverManager.getConnection(URL, USERNAME,PASSWORD);
		}catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public void addUser(Users user) {
		final String SQL="INSERT INTO Users(id, login, password, mail) VALUES (?,?,?,?)";
		
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getMail());
			statement.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveComment(Comments comment) {
		final String SQL="INSERT INTO Comments(id, text, date, Songsid, Usersid) VALUES (?,?,?,?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, comment.getId());
			statement.setString(2, comment.getText());
			statement.setObject(3, comment.getDate());
			statement.setInt(4, comment.getSongsId());
			statement.setInt(4, comment.getUsersId());
			statement.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Comments> getCommentsBySongId(Integer songId) {
		List<Comments> listComments=new ArrayList<>();
		final String SQL="SELECT * FROM Comments WHERE Songsid = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, songId);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Comments comment=new Comments(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getDate("data"), resultSet.getInt("Songsid"), resultSet.getInt("Usersid"));
				listComments.add(comment);
			}
			
			return listComments;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveMarks(Marks mark) {
		final String SQL="INSERT INTO Marks(id, reaction, Songsid) VALUES (?,?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, mark.getId());
			statement.setString(2, mark.getReaction());
			statement.setInt(3, mark.getSongsId());
			statement.executeUpdate(); 
			System.out.println("Mark save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Marks> getMarksBySongId(Integer songId) {
		List<Marks> listMarks=new ArrayList<>();
		final String SQL="SELECT * FROM Marks WHERE Songsid = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, songId);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Marks mark=new Marks(resultSet.getInt("id"), resultSet.getString("reaction"), resultSet.getInt("Songsid"));
				listMarks.add(mark);
			}
			
			return listMarks;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveHistory(History hist) {
		final String SQL="INSERT INTO History(Usersid, Songsid) VALUES (?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, hist.getUsersId());
			statement.setInt(2, hist.getSongsId());
			statement.executeUpdate(); 
			System.out.println("History save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<History> getHistoryByUserId(Integer userId) {
		List<History> listHistory=new ArrayList<>();
		final String SQL="SELECT * FROM History WHERE Usersid = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, userId);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				History hist=new History(resultSet.getInt("Usersid"), resultSet.getInt("Songsid"));
				listHistory.add(hist);
			}
			
			return listHistory;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveFavorites(Favorites fav) {
		final String SQL="INSERT INTO Favorites(Usersid, Songsid) VALUES (?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, fav.getUsersId());
			statement.setInt(2, fav.getSongsId());
			statement.executeUpdate(); 
			System.out.println("Favorites save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Favorites> getFavoritesByUserId(Integer userId) {
		System.out.println("getFavoritesByUserId start");
		List<Favorites> listFavorites=new ArrayList<>();
		System.out.println("3");
		final String SQL="SELECT * FROM Favorites WHERE Usersid = ?";
		System.out.println("#####################");
		System.out.println("query SQL formed");
		
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			System.out.println("#####################");
			System.out.println("connection succes");
			statement.setInt(1, userId);
			System.out.println("#####################");
			System.out.println("Data set");
			ResultSet resultSet=statement.executeQuery();
			System.out.println("#####################");
			System.out.println("resultSet:");
			System.out.println(resultSet);
			System.out.println("#####################");
			
			while(resultSet.next()) {
				Favorites fav=new Favorites(resultSet.getInt("Usersid"), resultSet.getInt("Songsid"));
				listFavorites.add(fav);
			}
			System.out.println("Data getted");
			System.out.println(listFavorites);
			return listFavorites;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void saveFoldersSongs(FoldersSongs folderSong) {
		final String SQL="INSERT INTO Folders_Songs(Foldersid, Songsid) VALUES (?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, folderSong.getFoldersId());
			statement.setInt(2, folderSong.getSongsId());
			statement.executeUpdate(); 
			System.out.println("FoldersSongs save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<FoldersSongs> getFoldersSongsByFoldersId(Integer foldersId) {
		List<FoldersSongs> listFoldersSongs=new ArrayList<>();
		final String SQL="SELECT * FROM Folders_Songs WHERE Foldersid = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, foldersId);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				FoldersSongs folder=new FoldersSongs(resultSet.getInt("Foldersid"), resultSet.getInt("Songsid"));
				listFoldersSongs.add(folder);
			}
			
			return listFoldersSongs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void saveTagsSongs(TagsSongs tagsSongs) {
		final String SQL="INSERT INTO Tags_Songs(Tagsid, Songsid) VALUES (?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, tagsSongs.getTagsId());
			statement.setInt(2, tagsSongs.getSongsId());
			statement.executeUpdate(); 
			System.out.println("TagsSongs save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<TagsSongs> getTagsSongsBySongsId(Integer songsId) {
		List<TagsSongs> listTagsSongs=new ArrayList<>();
		final String SQL="SELECT * FROM Tags_Songs WHERE Songsid = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, songsId);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				TagsSongs tagsSongs=new TagsSongs(resultSet.getInt("Tagsid"), resultSet.getInt("Songsid"));
				listTagsSongs.add(tagsSongs);
			}
			
			return listTagsSongs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void saveSongsSingers(SongsSingers songsSingers) {
		final String SQL="INSERT INTO Songs_Singers(Songsid, Singersid, link_to_video) VALUES (?,?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, songsSingers.getSongsId());
			statement.setInt(2, songsSingers.getSingersId());
			statement.setString(3, songsSingers.getLinkToVideo());
			statement.executeUpdate(); 
			System.out.println("SongsSingers save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<SongsSingers> getSongsSingersBySongsId(Integer songsId) {
		List<SongsSingers> listSongsSingers=new ArrayList<>();
		final String SQL="SELECT * FROM Songs_Singers WHERE Songsid = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, songsId);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				SongsSingers songsSingers=new SongsSingers(resultSet.getInt("Songsid"), resultSet.getInt("Singersid"), resultSet.getString("link_to_video"));
				listSongsSingers.add(songsSingers);
			}
			
			return listSongsSingers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveSongs(Songs songs) {
		final String SQL="INSERT INTO Songs(id, gener, name, musician, text, level_id, visitors_count) VALUES (?,?,?,?,?,?,?)";

		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, songs.getId());
			statement.setString(2, songs.getGener());
			statement.setString(3, songs.getName());
			statement.setString(4, songs.getMusician());
			statement.setString(5, songs.getText());
			statement.setInt(6, songs.getLevelId());
			statement.setInt(7, songs.getVisitorsCount());
			statement.executeUpdate(); 
			System.out.println("Songs save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Songs> getSongsByName(String name) {
		List<Songs> listSongs=new ArrayList<>();
		final String SQL="SELECT * FROM Songs WHERE name = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Songs songs=new Songs(resultSet.getInt("id"),resultSet.getString("gender"),resultSet.getString("name"),resultSet.getString("musician"),resultSet.getString("text"),resultSet.getInt("level_id"), resultSet.getInt("Visitors_count"));
				listSongs.add(songs);
			}
			
			return listSongs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveSingers(Singers singers) {
		final String SQL="INSERT INTO Singers(id, name, gender,text) VALUES (?,?,?,?)";
//		Integer id, String name, Byte gender, String text
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, singers.getId());
			statement.setString(2, singers.getName());
			statement.setByte(3, singers.getGender());
			statement.setString(4, singers.getText());
			statement.executeUpdate(); 
			System.out.println("Singers save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Singers> getSingersByName(String name) {
		List<Singers> listSingers=new ArrayList<>();
		final String SQL="SELECT * FROM Singers WHERE name = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Singers singers=new Singers(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getByte("gender"), resultSet.getString("text"));
				listSingers.add(singers);
			}
			
			return listSingers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveLevels(Levels level) {
		final String SQL="INSERT INTO Levels(id, level_name,info) VALUES (?,?,?)";
//		Integer id, String levelName, String info
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, level.getId());
			statement.setString(2, level.getLevelName());
			statement.setString(3, level.getInfo());
			statement.executeUpdate(); 
			System.out.println("level save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Levels> getLevelsByName(String name) {
		List<Levels> listLevels=new ArrayList<>();
		final String SQL="SELECT * FROM Levels WHERE name = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Levels level=new Levels(resultSet.getInt("id"), resultSet.getString("level_name"), resultSet.getString("info"));
				listLevels.add(level);
			}
			
			return listLevels;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void saveTags(Tags tag) {
		final String SQL="INSERT INTO Tags(id, name) VALUES (?,?)";

		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, tag.getId());
			statement.setString(2, tag.getName());
			statement.executeUpdate(); 
			System.out.println("Tags save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Tags> getTagsByName(String name) {
		List<Tags> listTags=new ArrayList<>();
		final String SQL="SELECT * FROM Tags WHERE name = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Tags tag=new Tags(resultSet.getInt("id"), resultSet.getString("name"));
				listTags.add(tag);
			}
			
			return listTags;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveFolders(Folders folder) {
		final String SQL="INSERT INTO Folders(id, name, Usersid) VALUES (?,?,?)";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setInt(1, folder.getId());
			statement.setString(2, folder.getName());
			statement.setInt(3, folder.getUserId());
			statement.executeUpdate(); 
			System.out.println("Folders save");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Folders> getFoldersByName(String name) {
		List<Folders> listFolders=new ArrayList<>();
		final String SQL="SELECT * FROM Folders WHERE name = ?";
		try {
			final PreparedStatement  statement=connection.prepareStatement(SQL);
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()) {
				Folders folder=new Folders(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("Usersid"));
				listFolders.add(folder);
			}
			
			return listFolders;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
