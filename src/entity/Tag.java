package entity;

public class Tag 
{
	private String tagName = null;
	
	public Tag(){}
	public Tag(String tagName)
	{
		setTagName(tagName);
	}
	public void setTagName(String tagName)
	{
		if(tagName.length() >= 200)
		{
			System.out.println("Error: Nome da tag ultrapassa 200 caracteres: " + tagName);
			return;
		}
		tagName = tagName.replaceAll("'", "''");
		tagName = tagName.trim();
		this.tagName = tagName;
	}
	public String getTagName()
	{
		return tagName;
	}
}
