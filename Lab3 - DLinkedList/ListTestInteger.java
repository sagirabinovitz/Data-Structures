public class ListTestInteger extends ListTest<Integer>{
	private int i=1;
	@Override
	public Integer getParameterInstance() {
		i++;
		return i;
	}

}
