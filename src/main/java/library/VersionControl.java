package library;

/**
 * API return whether version is bad.
 *
 * @author BorisMirage
 * Time: 2019/06/22 16:18
 * Created with IntelliJ IDEA
 */

public class VersionControl {
    /**
     * Constructor of class.
     * This class works as a blackbox of given API, therefore, it actually has no actual function.
     */
    public VersionControl() {

    }

    /**
     * API that tells if given version is a bad version.
     *
     * @param version version number
     * @return if current version is bad version
     */
    public boolean isBadVersion(int version) {
        return version / 2 == 0;
    }
}
