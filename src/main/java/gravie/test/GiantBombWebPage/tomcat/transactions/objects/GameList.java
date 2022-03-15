package gravie.test.GiantBombWebPage.tomcat.transactions.objects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameList {
	
	private String error;
	private Long limit;
	private Long number_of_page_results;
	private Long number_of_total_results;
	private Long status_code;
	private int offset = 0;
	private List<Game> results;
	private String searchName;
	
	public GameList() {
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Long getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Long status_code) {
		this.status_code = status_code;
	}
	public Long getLimit() {
		return limit;
	}
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	public Long getNumber_of_page_results() {
		return number_of_page_results;
	}
	public void setNumber_of_page_results(Long number_of_page_results) {
		this.number_of_page_results = number_of_page_results;
	}
	public Long getNumber_of_total_results() {
		return number_of_total_results;
	}
	public void setNumber_of_total_results(Long number_of_total_results) {
		this.number_of_total_results = number_of_total_results;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<Game> getResults() {
		return results;
	}
	public void setResults(List<Game> results) {
		this.results = results;
	}
	public int getCurrentPage() {
		int currentPage = 1;
		if(offset != 0) {
			currentPage = (int) (offset / number_of_page_results) + 1;
		}
		return currentPage;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	@Override
	public String toString() {
		return "GameList [error=" + error + ", limit=" + limit + ", number_of_page_results=" + number_of_page_results
				+ ", number_of_total_results=" + number_of_total_results + ", status_code=" + status_code + ", results="
				+ results + "]";
	}
}
