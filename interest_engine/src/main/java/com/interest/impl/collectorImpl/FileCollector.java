package com.interest.impl.collectorImpl;

import com.interest.model.Input;
import com.interest.model.Music;
import com.interest.model.MusicList;
import com.interest.service.Collector;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
@Service("fileCollector")
public class FileCollector implements Collector {
    private File file;
    private Input input;
    @Override
    public Input collect() {
        MusicList musicList = new MusicList();
        List<Music> musics = new ArrayList<Music>();
        for(String s:readNames(file)){
            musics.add(new Music(s));
        }
        musicList.setMusics(musics);
        input = musicList;
        return musicList;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileCollector(){

    }
    public List<String> readNames(File file) {
        List<String> result = new ArrayList<String>();
        try {
            SAXReader reader = new SAXReader();
            Document   doc = reader.read(file);
            Element root = doc.getRootElement();
            List nodes=root.elements("File");
            for(Iterator it = nodes.iterator();it.hasNext();){
                Element element = (Element)it.next();
                result.add(element.element("FileName").getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return result;
    }
}
