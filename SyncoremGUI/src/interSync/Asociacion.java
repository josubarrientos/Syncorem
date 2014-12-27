/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interSync;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alumno
 */
public class Asociacion {
    private String DirPc;
    private String ID;
    private String DirExt;
	public String getDirPc() {
		return DirPc;
	}
	public void setDirPc(String DirPc) {
		this.DirPc = DirPc;
	}
        public String getId() {
		return ID;
	}
	public void setId(String Id) {
		this.ID=Id;
	}
	public String getDirExt() {
		return DirExt;
	}

	public void setDirExt(String newdir) {
                this.DirExt=newdir;
	}
}
           

