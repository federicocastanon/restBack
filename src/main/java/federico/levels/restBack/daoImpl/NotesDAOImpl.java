package federico.levels.restBack.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import federico.levels.restBack.dao.NotesDAO;
import federico.levels.restBack.domain.Note;
@Service
@Qualifier("firstService")
@Repository
public class NotesDAOImpl implements NotesDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String INSERT_MESSAGE_SQL 
	  = "insert into notes (body) values(?)";	
	
	@Override
	public Note isAliveAndWell() {
		return getNoteById(Long.valueOf(1));
	}

	@Override
	public Note getNoteById(Long id) {
		return jdbcTemplate.queryForObject("select * from notes where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Note>(Note.class));
	}

	@Override
	public Note saveNote(Note newNote) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	                .prepareStatement(INSERT_MESSAGE_SQL);
	                ps.setString(1, newNote.getBody());
	                return ps;
	              }, keyHolder); 
		newNote.setId(keyHolder.getKey().longValue());
		//returning same object with new ID
		return newNote;
	}

	@Override
	public int deleteNote(Long id) {
		return jdbcTemplate.update("delete from notes where id=?", new Object[] { id });
	}

	class NoteRowMapper implements RowMapper<Note>{
		@Override
		public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
			Note note = new Note();
			note.setId(rs.getLong("id"));
			note.setBody(rs.getString("body"));
			return note;
		}
	}
	
	@Override
	public List<Note> getNotesByBody(String query) {
		if(query == null){
			return jdbcTemplate.query("select * from notes",
					new NoteRowMapper());
		}else{
			return jdbcTemplate.query("select * from notes where body like ?", new Object[] { "%"+query+"%" },
					new NoteRowMapper());
		}		
	}

	@Override
	public int updateNote(Note note) {
		return jdbcTemplate.update("update notes set body = ? where id = ?", new Object[] { note.getBody(), note.getId() });
	}
	


}