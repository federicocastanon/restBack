package federico.levels.restBack.controllersImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import federico.levels.restBack.controllers.NotesController;
import federico.levels.restBack.dao.NotesDAO;
import federico.levels.restBack.domain.Note;
import federico.levels.restBack.domain.ReturnMessage;

@RestController
public class NotesContollerImpl implements NotesController {

	@Autowired
	NotesDAO dao;

	@Override
	public ReturnMessage isAlive() {
		return new ReturnMessage(0, "alive");
	}

	@Override
	public Note dbAliveAndWell() {
		return dao.isAliveAndWell();
	}

	@Override
	public Note getNoteById(Long id) {
		return dao.getNoteById(id);
	}

	@Override
	public Note createNote(Note newNote) {
		return dao.saveNote(newNote);
	}

	@Override
	public ReturnMessage deleteNote(Long id) {
		int deleted = dao.deleteNote(id);
		ReturnMessage rm = new ReturnMessage();
		if (deleted > 0){
			rm.setBody("Note succesfully deleted!");
			rm.setErrorCode(0);
		}else{
			rm.setBody("Note not found");
			rm.setErrorCode(1);
		}
		return rm;
	}



	@Override
	public List<Note> getNotes(String query) {
		return dao.getNotesByBody(query);
	}

	@Override
	public ReturnMessage updateNote(Note note) {
		int updated = dao.updateNote(note);
		
		ReturnMessage rm = new ReturnMessage();
		if (updated > 0){
			rm.setBody("Note succesfully updated!");
			rm.setErrorCode(0);
		}else{
			rm.setBody("Note not found");
			rm.setErrorCode(1);
		}
		return rm; 
	}

}
