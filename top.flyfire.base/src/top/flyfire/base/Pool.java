package top.flyfire.base;

import java.io.Serializable;

public interface Pool<P extends Pool<?>> {
	int PASSAGE_NUM = 5;
	P get(Serializable serializable);
	P get(Serializable serializable, int passageNum);
	P get(Serializable serializable, boolean forcible);
	P get(Serializable serializable, boolean forcible ,int passageNum);
}
