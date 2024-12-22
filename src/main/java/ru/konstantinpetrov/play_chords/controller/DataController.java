package ru.konstantinpetrov.play_chords.controller;

import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.konstantinpetrov.play_chords.DB.DBManager;
import ru.konstantinpetrov.play_chords.dtoLayer.*;
import ru.konstantinpetrov.play_chords.entity.*;

@Slf4j
@RestController
public class DataController {

	private final DBManager dbManager;

	public DataController(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	
	@PostMapping(path="/save_comment/{comment}")
	public ResponseEntity<ResponseEnterDTO> saveComment(@PathVariable Comments comment) {
		try {
			log.info("Comment getted", comment);
			this.dbManager.saveComment(comment);
			System.out.println("Saved Comment");
			return new ResponseEntity<>(new ResponseEnterDTO(comment.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your comment", e);
            return new ResponseEntity<>(new ResponseEnterDTO(comment.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_comment/{songId}")
	public ResponseEntity<ResponseCommentsDTO> getComment(@PathVariable Integer songId) {
		try {
//			this.dbManager.saveComment(comment);
			log.info("Request for comment received");
			List<Comments> comment=this.dbManager.getCommentsBySongId(songId);

			log.info("comment sent");
			return new ResponseEntity<>(new ResponseCommentsDTO(comment),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your comment", e);
            return new ResponseEntity<>(new ResponseCommentsDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_marks/{mark}")
	public ResponseEntity<ResponseEnterDTO> saveMarks(@PathVariable Marks mark) {
		try {
			log.info("Mark getted", mark);
			this.dbManager.saveMarks(mark);
			System.out.println("Saved Comment");
			return new ResponseEntity<>(new ResponseEnterDTO(mark.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your mark", e);
            return new ResponseEntity<>(new ResponseEnterDTO(mark.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_marks/{songId}")
	public ResponseEntity<ResponseMarksDTO> getMarks(@PathVariable Integer songId) {
		try {
			log.info("Request for mark received");
			List<Marks> mark=this.dbManager.getMarksBySongId(songId);
			log.info("mark sent");
			return new ResponseEntity<>(new ResponseMarksDTO(mark),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your mark", e);
            return new ResponseEntity<>(new ResponseMarksDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_history/{hist}")
	public ResponseEntity<ResponseEnterDTO> saveHistory(@PathVariable History hist ) {
		try {
			log.info("History getted", hist);
			this.dbManager.saveHistory(hist);
			System.out.println("Saved History");
			return new ResponseEntity<>(new ResponseEnterDTO(hist.getUsersId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your history", e);
            return new ResponseEntity<>(new ResponseEnterDTO(hist.getUsersId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_history/{userId}")
	public ResponseEntity<ResponseHistoryDTO> getHistory(@PathVariable Integer userId) {
		try {
			log.info("Request for history received");
			List<History> hist=this.dbManager.getHistoryByUserId(userId);
			log.info("History sent");
			return new ResponseEntity<>(new ResponseHistoryDTO(hist),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your history", e);
            return new ResponseEntity<>(new ResponseHistoryDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_favorites/{fav}")
	public ResponseEntity<ResponseEnterDTO> saveFavorites(@PathVariable Favorites fav ) {
		try {
			log.info("Favorites getted", fav);
			this.dbManager.saveFavorites(fav);
			System.out.println("Saved Favorites");
			return new ResponseEntity<>(new ResponseEnterDTO(fav.getUsersId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your favorites", e);
            return new ResponseEntity<>(new ResponseEnterDTO(fav.getUsersId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/get_favorites/{userId}")
	public ResponseEntity<ResponseFavoritesDTO> getFavorites(@PathVariable Integer userId) {
		try {
			log.info("Request for favorites received");
			List<Favorites> fav=this.dbManager.getFavoritesByUserId(userId);
			log.info("favorites sent");
			return new ResponseEntity<>(new ResponseFavoritesDTO(fav),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your favorites", e);
            return new ResponseEntity<>(new ResponseFavoritesDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_folders_songs/{folder}")
	public ResponseEntity<ResponseEnterDTO> saveFoldersSongs(@PathVariable FoldersSongs folder ) {
		try {
			log.info("Folder getted", folder);
			this.dbManager.saveFoldersSongs(folder);
			System.out.println("Saved Favorites");
			return new ResponseEntity<>(new ResponseEnterDTO(folder.getFoldersId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your folder", e);
            return new ResponseEntity<>(new ResponseEnterDTO(folder.getFoldersId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_folders_songs/{foldersId}")
	public ResponseEntity<ResponseFoldersSongsDTO> getFoldersSongs(@PathVariable Integer foldersId) {
		try {
			log.info("Request for folder received");
			List<FoldersSongs> folder=this.dbManager.getFoldersSongsByFoldersId(foldersId);
			log.info("Songs from your folder sent");
			return new ResponseEntity<>(new ResponseFoldersSongsDTO(folder),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your songs", e);
            return new ResponseEntity<>(new ResponseFoldersSongsDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_tags_songs/{tagsSongs}")
	public ResponseEntity<ResponseEnterDTO> saveTagsSongs(@PathVariable TagsSongs tagsSongs ) {
		try {
			log.info("Tag Songs getted", tagsSongs);
			this.dbManager.saveTagsSongs(tagsSongs);
			return new ResponseEntity<>(new ResponseEnterDTO(tagsSongs.getTagsId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your Song's tag", e);
            return new ResponseEntity<>(new ResponseEnterDTO(tagsSongs.getTagsId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	

	@GetMapping(path="/get_tags_songs/{songsId}")
	public ResponseEntity<ResponseTagsSongsDTO> getTagsSongs(@PathVariable Integer songsId) {
		try {
			log.info("Request for Tag Songs received");
			List<TagsSongs> tagsSongs=this.dbManager.getTagsSongsBySongsId(songsId);
			log.info("Tag Songs sent");
			return new ResponseEntity<>(new ResponseTagsSongsDTO(tagsSongs),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your tag songs", e);
            return new ResponseEntity<>(new ResponseTagsSongsDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_songs_singers/{songsSingers}")
	public ResponseEntity<ResponseEnterDTO> saveSongsSingers(@PathVariable SongsSingers songsSingers ) {
		try {
			log.info("Songs singers getted", songsSingers);
			this.dbManager.saveSongsSingers(songsSingers);
			System.out.println("Saved SongsSingers");
			return new ResponseEntity<>(new ResponseEnterDTO(songsSingers.getSongsId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your songs singers", e);
            return new ResponseEntity<>(new ResponseEnterDTO(songsSingers.getSongsId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_songs_singers/{songsId}")
	public ResponseEntity<ResponseSongsSingersDTO> getSongsSingers(@PathVariable Integer songsId) {
		try {
			log.info("Request for songs singers received");
			List<SongsSingers> songsSingers=this.dbManager.getSongsSingersBySongsId(songsId);
			log.info("Songs singers sent");
			return new ResponseEntity<>(new ResponseSongsSingersDTO(songsSingers),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your songs singers", e);
            return new ResponseEntity<>(new ResponseSongsSingersDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_songs/{songs}")
	public ResponseEntity<ResponseEnterDTO> saveSongs(@PathVariable Songs songs ) {
		try {
			log.info("Songs getted", songs);
			this.dbManager.saveSongs(songs);
			System.out.println("Saved Songs");
			return new ResponseEntity<>(new ResponseEnterDTO(songs.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your songs", e);
            return new ResponseEntity<>(new ResponseEnterDTO(songs.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_songs/{name}")
	public ResponseEntity<ResponseSongsDTO> getSongs(@PathVariable String name) {
		try {
			log.info("Request for songs received");
			List<Songs> songs=this.dbManager.getSongsByName(name);
			log.info("Songs sent");
			return new ResponseEntity<>(new ResponseSongsDTO(songs),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your songs", e);
            return new ResponseEntity<>(new ResponseSongsDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_singers/{singers}")
	public ResponseEntity<ResponseEnterDTO> saveSingers(@PathVariable Singers singers ) {
		try {
			log.info("Singers getted", singers);
			this.dbManager.saveSingers(singers);
			System.out.println("Saved Singers");
			return new ResponseEntity<>(new ResponseEnterDTO(singers.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your singers", e);
            return new ResponseEntity<>(new ResponseEnterDTO(singers.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_singers/{name}")
	public ResponseEntity<ResponseSingersDTO> getSingers(@PathVariable String name) {
		try {
			log.info("Request for singers received");
			List<Singers> singers=this.dbManager.getSingersByName(name);
			log.info("Singers sent");
			return new ResponseEntity<>(new ResponseSingersDTO(singers),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your singers", e);
            return new ResponseEntity<>(new ResponseSingersDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_levels/{level}")
	public ResponseEntity<ResponseEnterDTO> saveLevels(@PathVariable Levels level ) {
		try {
			log.info("Levels getted", level);
			this.dbManager.saveLevels(level);
			return new ResponseEntity<>(new ResponseEnterDTO(level.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your level", e);
            return new ResponseEntity<>(new ResponseEnterDTO(level.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_levels/{name}")
	public ResponseEntity<ResponseLevelsDTO> getLevels(@PathVariable String name) {
		try {
			log.info("Request for level received");
			List<Levels> level=this.dbManager.getLevelsByName(name);
			log.info("Levels sent");
			return new ResponseEntity<>(new ResponseLevelsDTO(level),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your level", e);
            return new ResponseEntity<>(new ResponseLevelsDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_tags/{tag}")
	public ResponseEntity<ResponseEnterDTO> saveTags(@PathVariable Tags tag ) {
		try {
			log.info("Tag getted", tag);
			this.dbManager.saveTags(tag);
			System.out.println("Saved Tags");
			return new ResponseEntity<>(new ResponseEnterDTO(tag.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your tag", e);
            return new ResponseEntity<>(new ResponseEnterDTO(tag.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_tags/{name}")
	public ResponseEntity<ResponseTagsDTO> getTags(@PathVariable String name) {
		try {
			log.info("Request for tag received");
			List<Tags> tag=this.dbManager.getTagsByName(name);
			log.info("Tag sent");
			return new ResponseEntity<>(new ResponseTagsDTO(tag),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your tag", e);
            return new ResponseEntity<>(new ResponseTagsDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PostMapping(path="/save_folders/{folder}")
	public ResponseEntity<ResponseEnterDTO> saveFolders(@PathVariable Folders folder ) {
		try {
			log.info("Folder getted", folder);
			this.dbManager.saveFolders(folder);
			System.out.println("Saved Folders");
			return new ResponseEntity<>(new ResponseEnterDTO(folder.getId()),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error saving your folder", e);
            return new ResponseEntity<>(new ResponseEnterDTO(folder.getId()),
                    HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@GetMapping(path="/get_folders/{name}")
	public ResponseEntity<ResponseFoldersDTO> getFolders(@PathVariable String name) {
		try {
			log.info("Request for folder received");
			List<Folders> folder=this.dbManager.getFoldersByName(name);
			log.info("Folder sent");
			return new ResponseEntity<>(new ResponseFoldersDTO(folder),
                 HttpStatus.OK);
        } catch (Exception e) {
			log.error("There was an error find your folder", e);
            return new ResponseEntity<>(new ResponseFoldersDTO(null),
                    HttpStatus.BAD_REQUEST);
        }
	}
}
