package myscript.ws.api.hwr;
/**
 * MyScript Cloud Sample
 */
import java.util.List;

public class HWRSegment {
	public List<String> inkRanges;
	public List<HWRCandidate> candidates;
	public int candidatesSize;
	public int selectedCandidateIdx;

	public List<String> getInkRanges() {
		return inkRanges;
	}

	public void setInkRanges(List<String> inkRanges) {
		this.inkRanges = inkRanges;
	}

	public List<HWRCandidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<HWRCandidate> candidates) {
		this.candidates = candidates;
	}

	public int getCandidatesSize() {
		return candidatesSize;
	}

	public void setCandidatesSize(int candidatesSize) {
		this.candidatesSize = candidatesSize;
	}

	public int getSelectedCandidateIdx() {
		return selectedCandidateIdx;
	}

	public void setSelectedCandidateIdx(int selectedCandidateIdx) {
		this.selectedCandidateIdx = selectedCandidateIdx;
	}

}
