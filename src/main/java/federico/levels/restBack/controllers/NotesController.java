package federico.levels.restBack.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import federico.levels.restBack.domain.Note;
import federico.levels.restBack.domain.ReturnMessage;


@RequestMapping("/api/notes")
public interface NotesController {

	@GetMapping("/alive")
	public ReturnMessage isAlive();
	
	@GetMapping("/dbAliveAndWell")
	public Note dbAliveAndWell();
	
	@GetMapping("/{id}")
	public Note getNoteById(@PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public ReturnMessage deleteNote(@PathVariable Long id);
	
	@PostMapping("/")
	public Note createNote(@RequestBody @Valid Note newNote);
	
	@PutMapping("/")
	public ReturnMessage updateNote(@RequestBody @Valid Note note);
	
	@GetMapping("/")
	public List<Note> getNotes(@RequestParam(required = false) String query);
	
}
