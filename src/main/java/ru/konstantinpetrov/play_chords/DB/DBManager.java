package ru.konstantinpetrov.play_chords.DB;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import ru.konstantinpetrov.play_chords.entity.*;
import ru.konstantinpetrov.play_chords.util.GostEncryptionUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
public class DBManager {
	private final DataSource dataSource;
	private final GostEncryptionUtil gostEncryptionUtil;
	private Connection connection;

	public DBManager(DataSource dataSource, GostEncryptionUtil gostEncryptionUtil) {
		this.dataSource = dataSource;
		this.gostEncryptionUtil = gostEncryptionUtil;
	}

	@PostConstruct
	public void init() {
		try {
			this.connection = dataSource.getConnection();
			log.info("Сonnection established");
		} catch (SQLException e) {
			log.error("Some problem with establish connection", e);
			e.printStackTrace();
		}
	}

	// Сохранение пользователей не кэшируем, но после добавления данных можно очистить релевантный кэш, если он есть
	public void addUser(Users user) {
		final String SQL="INSERT INTO Users(id, login, password, mail) VALUES (?,?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, user.getId());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getMail());
			statement.executeUpdate();
			log.info("User added", user);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	@CacheEvict(value = "comments", key = "#comment.songsId")
	public void saveComment(Comments comment) {
		final String SQL="INSERT INTO Comments(id, text, date, Songsid, Usersid) VALUES (?,?,?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			String originalText = comment.getText();
			String encryptedText = gostEncryptionUtil.encrypt(originalText);
			comment.setText(encryptedText);

			statement.setInt(1, comment.getId());
			statement.setString(2, comment.getText());
			statement.setObject(3, comment.getDate());
			statement.setInt(4, comment.getSongsId());
			statement.setInt(5, comment.getUsersId());
			statement.executeUpdate();
			log.info("Comment added", comment);
		} catch (Exception e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	@Cacheable(value = "comments", key = "#songId")
	public List<Comments> getCommentsBySongId(Integer songId) {
		List<Comments> listComments=new ArrayList<>();
		final String SQL="SELECT * FROM Comments WHERE Songsid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, songId);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				String encryptedText = resultSet.getString("text");
				String decryptedText = gostEncryptionUtil.decrypt(encryptedText);

				Comments comment=new Comments(
						resultSet.getInt("id"),
						decryptedText,
						resultSet.getDate("date"),
						resultSet.getInt("Songsid"),
						resultSet.getInt("Usersid")
				);
				listComments.add(comment);
			}
			log.info("Comments finded", listComments);
			return listComments;
		} catch (Exception e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveMarks(Marks mark) {
		final String SQL="INSERT INTO Marks(id, reaction, Songsid) VALUES (?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, mark.getId());
			statement.setString(2, mark.getReaction());
			statement.setInt(3, mark.getSongsId());
			statement.executeUpdate();
			log.info("Mark added", mark);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<Marks> getMarksBySongId(Integer songId) {
		List<Marks> listMarks=new ArrayList<>();
		final String SQL="SELECT * FROM Marks WHERE Songsid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, songId);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Marks mark=new Marks(
						resultSet.getInt("id"),
						resultSet.getString("reaction"),
						resultSet.getInt("Songsid")
				);
				listMarks.add(mark);
			}
			log.info("Marks finded", listMarks);
			return listMarks;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveHistory(History hist) {
		final String SQL="INSERT INTO History(Usersid, Songsid) VALUES (?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, hist.getUsersId());
			statement.setInt(2, hist.getSongsId());
			statement.executeUpdate();
			log.info("History record added", hist);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<History> getHistoryByUserId(Integer userId) {
		List<History> listHistory=new ArrayList<>();
		final String SQL="SELECT * FROM History WHERE Usersid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, userId);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				History hist=new History(
						resultSet.getInt("Usersid"),
						resultSet.getInt("Songsid")
				);
				listHistory.add(hist);
			}
			log.info("History finded", listHistory);
			return listHistory;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveFavorites(Favorites fav) {
		final String SQL="INSERT INTO Favorites(Usersid, Songsid) VALUES (?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, fav.getUsersId());
			statement.setInt(2, fav.getSongsId());
			statement.executeUpdate();
			log.info("Favorites songs added", fav);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<Favorites> getFavoritesByUserId(Integer userId) {
		List<Favorites> listFavorites=new ArrayList<>();
		final String SQL="SELECT * FROM Favorites WHERE Usersid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, userId);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Favorites fv=new Favorites(
						resultSet.getInt("Usersid"),
						resultSet.getInt("Songsid")
				);
				listFavorites.add(fv);
			}
			log.info("Favorites songs finded", listFavorites);
			return listFavorites;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveFoldersSongs(FoldersSongs folderSong) {
		final String SQL="INSERT INTO Folders_Songs(Foldersid, Songsid) VALUES (?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, folderSong.getFoldersId());
			statement.setInt(2, folderSong.getSongsId());
			statement.executeUpdate();
			log.info("FoldersSongs added", folderSong);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<FoldersSongs> getFoldersSongsByFoldersId(Integer foldersId) {
		List<FoldersSongs> listFoldersSongs=new ArrayList<>();
		final String SQL="SELECT * FROM Folders_Songs WHERE Foldersid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, foldersId);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				FoldersSongs folder=new FoldersSongs(
						resultSet.getInt("Foldersid"),
						resultSet.getInt("Songsid")
				);
				listFoldersSongs.add(folder);
			}
			log.info("Folders songs finded", listFoldersSongs);
			return listFoldersSongs;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveTagsSongs(TagsSongs tagsSongs) {
		final String SQL="INSERT INTO Tags_Songs(Tagsid, Songsid) VALUES (?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, tagsSongs.getTagsId());
			statement.setInt(2, tagsSongs.getSongsId());
			statement.executeUpdate();
			log.info("TagsSongs finded", tagsSongs);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<TagsSongs> getTagsSongsBySongsId(Integer songsId) {
		List<TagsSongs> listTagsSongs=new ArrayList<>();
		final String SQL="SELECT * FROM Tags_Songs WHERE Songsid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, songsId);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				TagsSongs ts=new TagsSongs(
						resultSet.getInt("Tagsid"),
						resultSet.getInt("Songsid")
				);
				listTagsSongs.add(ts);
			}
			log.info("Tags Songs finded", listTagsSongs);
			return listTagsSongs;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveSongsSingers(SongsSingers songsSingers) {
		final String SQL="INSERT INTO Songs_Singers(Songsid, Singersid, link_to_video) VALUES (?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, songsSingers.getSongsId());
			statement.setInt(2, songsSingers.getSingersId());
			statement.setString(3, songsSingers.getLinkToVideo());
			statement.executeUpdate();
			log.info("Songs Singers save", songsSingers);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<SongsSingers> getSongsSingersBySongsId(Integer songsId) {
		List<SongsSingers> listSongsSingers=new ArrayList<>();
		final String SQL="SELECT * FROM Songs_Singers WHERE Songsid = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, songsId);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				SongsSingers ss=new SongsSingers(
						resultSet.getInt("Songsid"),
						resultSet.getInt("Singersid"),
						resultSet.getString("link_to_video")
				);
				listSongsSingers.add(ss);
			}
			log.info("Songs Singers finded", listSongsSingers);
			return listSongsSingers;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	@CacheEvict(value = "songs", key = "#songs.name")
	public void saveSongs(Songs songs) {
		final String SQL="INSERT INTO Songs(id, gener, name, musician, text, level_id, visitors_count) VALUES (?,?,?,?,?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, songs.getId());
			statement.setString(2, songs.getGener());
			statement.setString(3, songs.getName());
			statement.setString(4, songs.getMusician());
			statement.setString(5, songs.getText());
			statement.setInt(6, songs.getLevelId());
			statement.setInt(7, songs.getVisitorsCount());
			statement.executeUpdate();
			log.info("Songs save", songs);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	@Cacheable(value = "songs", key = "#name")
	public List<Songs> getSongsByName(String name) {
		List<Songs> listSongs=new ArrayList<>();
		final String SQL="SELECT * FROM Songs WHERE name = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Songs s=new Songs(
						resultSet.getInt("id"),
						resultSet.getString("gender"),
						resultSet.getString("name"),
						resultSet.getString("musician"),
						resultSet.getString("text"),
						resultSet.getInt("level_id"),
						resultSet.getInt("Visitors_count")
				);
				listSongs.add(s);
			}
			log.info("Songs find", listSongs);
			return listSongs;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveSingers(Singers singers) {
		final String SQL="INSERT INTO Singers(id, name, gender, text) VALUES (?,?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, singers.getId());
			statement.setString(2, singers.getName());
			statement.setByte(3, singers.getGender());
			statement.setString(4, singers.getText());
			statement.executeUpdate();
			log.info("Singers save", singers);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	@Cacheable(value = "singers", key = "#name")
	public List<Singers> getSingersByName(String name) {
		List<Singers> listSingers=new ArrayList<>();
		final String SQL="SELECT * FROM Singers WHERE name = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Singers sg=new Singers(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getByte("gender"),
						resultSet.getString("text")
				);
				listSingers.add(sg);
			}
			log.info("Singers find", listSingers);
			return listSingers;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveLevels(Levels level) {
		final String SQL="INSERT INTO Levels(id, level_name, info) VALUES (?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, level.getId());
			statement.setString(2, level.getLevelName());
			statement.setString(3, level.getInfo());
			statement.executeUpdate();
			log.info("Level save", level);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<Levels> getLevelsByName(String name) {
		List<Levels> listLevels=new ArrayList<>();
		final String SQL="SELECT * FROM Levels WHERE name = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Levels lvl=new Levels(
						resultSet.getInt("id"),
						resultSet.getString("level_name"),
						resultSet.getString("info")
				);
				listLevels.add(lvl);
			}
			log.info("Levels find", listLevels);
			return listLevels;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveTags(Tags tag) {
		final String SQL="INSERT INTO Tags(id, name) VALUES (?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, tag.getId());
			statement.setString(2, tag.getName());
			statement.executeUpdate();
			log.info("Tags save", tag);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<Tags> getTagsByName(String name) {
		List<Tags> listTags=new ArrayList<>();
		final String SQL="SELECT * FROM Tags WHERE name = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Tags tg=new Tags(
						resultSet.getInt("id"),
						resultSet.getString("name")
				);
				listTags.add(tg);
			}
			log.info("Tags find", listTags);
			return listTags;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}

	public void saveFolders(Folders folder) {
		final String SQL="INSERT INTO Folders(id, name, Usersid) VALUES (?,?,?)";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setInt(1, folder.getId());
			statement.setString(2, folder.getName());
			statement.setInt(3, folder.getUserId());
			statement.executeUpdate();
			log.info("Folders save", folder);
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
		}
	}

	public List<Folders> getFoldersByName(String name) {
		List<Folders> listFolders=new ArrayList<>();
		final String SQL="SELECT * FROM Folders WHERE name = ?";
		try (PreparedStatement statement=connection.prepareStatement(SQL)) {
			statement.setString(1, name);
			ResultSet resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Folders f=new Folders(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getInt("Usersid")
				);
				listFolders.add(f);
			}
			log.info("Folders find", listFolders);
			return listFolders;
		} catch (SQLException e) {
			log.error("Error work with data base", e);
			e.printStackTrace();
			return null;
		}
	}
}