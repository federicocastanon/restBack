package federico.levels.restBack.dao;

import java.util.List;

import federico.levels.restBack.domain.Note;

public interface NotesDAO {

	public Note isAliveAndWell();

	public Note getNoteById(Long id);

	public Note saveNote(Note newNote);

	public int deleteNote(Long id);

	public List<Note> getNotesByBody(String query);

	public int updateNote(Note note);
	
	
}
