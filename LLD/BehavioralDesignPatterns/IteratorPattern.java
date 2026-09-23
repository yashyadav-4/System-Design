package LLD.BehavioralDesignPatterns;

import java.util.*;

class Video{
    private String title;

    public Video(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

interface PlayList{
    PlayListIterator createIterator();
}

class YoutubePlaylist implements PlayList{
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }

    @Override
    public PlayListIterator createIterator() {
        return new YoutubePlaylistIterator(videos);
    }
}

interface PlayListIterator{
    boolean hasNext();
    Video next();
}

class YoutubePlaylistIterator implements PlayListIterator{
    private List<Video> videos;
    private int position;

    public YoutubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        position = 0;
    }

    public boolean hasNext(){
        return position < videos.size() ? true : false;
    }

    public Video next(){
        return hasNext()? videos.get(position++) : null;
    }
}

public class IteratorPattern {
    public static void main(String[] args) {

        YoutubePlaylist playlist = new YoutubePlaylist();
        playlist.addVideo(new Video("kkkkkkkkk"));
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        PlayListIterator iterator = playlist.createIterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
    }
}
