package am.MyBean;

public class Room {
	
	String campus;
	String building;
	String room;
	int volume;
	String date;
	String time;
	int roomid;

	
	public void setCampus(String cam)
	{
		campus=cam;
	}
	public String getCampus()
	{
		return campus;
	}
	public void setBuilding(String bui)
	{
		building=bui;
	}
	public String getBuilding()
	{
		return building;
	}
	public void setRoom(String name)
	{
		room=name;
	}
	public String getRoom()
	{
		return room;
	}
	
	public void setVolume(int num)
	{
		volume=num;
	}
	public int getVolume()
	{
		return volume;
	}
	
	public void setDate(String year)
	{
		date=year;
	}
	public String getDate()
	{
		return date;
	}
	public void setTime(String mor)
	{
		time=mor;
	}
	public String getTime()
	{
		return time;
	}
	public void setRoomid(int num)
	{
		roomid=num;
	}
	public int getRoomid()
	{
		return roomid;
	}

	

}
