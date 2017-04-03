/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.counter.counter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author zion
 */
@RestController
@RequestMapping("/counter-api/*")
public class MainController {
    @RequestMapping(value="/test", method = {RequestMethod.GET})
    public String root() {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>REST API Demo</b> </br>");
        sb.append("API end points with examples are:</br>");
        sb.append("/api/Token</br>");
        sb.append("/api/Fibonacci?n=8</br>");
        sb.append("/api/ReverseWords?sentence=hello%20world</br>");
        sb.append("/api/TriangleType?a=10&b=10&c=5</br>");

        return sb.toString();
    }
    
    @RequestMapping(value="/search", method = {RequestMethod.POST})
    public ResponseEntity<String> searchText(@RequestParam List<String> searchText) throws JsonProcessingException, JSONException{
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=utf-8");
        
        String rVal="";
        //System.out.println("searchText-> " + searchText.size());
        List<String> tempList = new ArrayList();
        Map<String,Integer> map;
        JSONObject jsonMap = new JSONObject();
        for(String st:searchText){
           Integer occurence =0;
           map = new HashMap();
           for (int i = sourceText.indexOf(st); i >= 0; i = sourceText.indexOf(st, i + 1))
               occurence++;
           map.put(st, occurence);
           jsonMap.put(st, occurence);
           String json = new ObjectMapper().writeValueAsString(map);
           System.out.println(json);
           tempList.add(json);
        }
        
        StringBuilder sb = new StringBuilder();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonMap);

        System.out.println(jsonArray.toString());
        for (String s : tempList)
        {
            sb.append(s);
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        
        //String json = new ObjectMapper().writeValueAsString(map);
        //System.out.println(json);
        return new ResponseEntity<>("["+sb.toString()+"]", httpHeaders, HttpStatus.OK);
    }
    @RequestMapping(value="/top/{t}", method = {RequestMethod.GET})
    public ResponseEntity<String> top(@PathVariable Integer t){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/csv;charset=utf-8");
        List<Word> ss = null;
        StringBuilder tempSb = new StringBuilder();
        ss = findTopWords();
        for(int i=0;i<=t;i++){
            Word word = ss.get(i);
            tempSb.append(word.word + "|" + word.count+ " ");
        }
        //return tempSb.toString();
        return new ResponseEntity<>(tempSb.toString(), httpHeaders, HttpStatus.OK);
    }
    
    private List<Word> findTopWords(){
        Map<String, Word> countMap = new HashMap<String, Word>();
        StringBuilder tempSource = new StringBuilder(sourceText.toString());
        StringBuilder tempSource1 = new StringBuilder();
        StringBuilder tempSource2 = new StringBuilder();
        for (int i = tempSource.indexOf("."); i >= 0; i = tempSource.indexOf(".", i + 1))
            tempSource1 = tempSource.deleteCharAt(i);
        for (int i = tempSource1.indexOf(","); i >= 0; i = tempSource1.indexOf(",", i + 1))
            tempSource2 = tempSource1.deleteCharAt(i);
        String line = tempSource2.toString();
		//while ((line = reader.readLine()) != null) {
			String[] words = line.split(" ");
			for (String word : words) {
                            
                                word = word.toLowerCase();
				if ("".equals(word)) {
					continue;
				}

				Word wordObj = countMap.get(word);
				if (wordObj == null) {
					wordObj = new Word();
					wordObj.word = word;
					wordObj.count = 0;
					countMap.put(word, wordObj);
				}

				wordObj.count++;
			}
        //SortedSet<Word> sortedWords = new TreeSet<Word>(countMap.values());
        List<Word> l = new ArrayList<>(countMap.values());
        Collections.sort(l);
        return l;
    }
    
    private StringBuilder sourceText = new StringBuilder("Lorem ipsum dolor sit amet, consecteturadipiscingelit. Nulla sed suscipitmetus, sit amettristiquepurus. Etiam sit ametleosollicitudin, tinciduntlectusvel, ultriciesmauris. Donecultrices lorem in esteleifend, et feugiat libero semper. Duis sodales gravida sapienegetefficitur. Utmattismollisblandit. Duis necmetus gravida, posueredolor id, pretiumurna. Aliquam vitae purus ex. Etiam vitae ipsum leo. Integer blandit, arcuegetcommodoscelerisque, risusleoaliquetdiam, in sagittismetus ex sed elit. Duis velurna non estfringillarutrum. Utmolestie sed risus in pharetra. Maecenas eget ante at nullafeugiateuismod. Suspendisse pharetra porttitorlacus non tristique. Vivamusvariusposuere ligula. Nullam magna metus, elementumvelelementumeu, elementum non magna. Ut cursus arcuvel ligula mollis, in interdumvelit maximus. Pellentesquearcu lorem, porttitor et quam vitae, imperdietvenenatis magna. Etiamimperdieteratvellectusrhoncussollicitudin. Praesent at mi a estsuscipittempor sed eu diam. In hachabitasseplateadictumst. Morbierat mi, iaculis id hendrerit a, sollicitudin et ligula. Vivamusjustonibh, cursus at ultricies sed, variusiaculisenim. Donecconsequatluctussapien, quisaliquam ante tristique sit amet. Pellentesqueaccumsansollicitudin mi a blandit. Donec ac dui bibendum, pharetra nulla vitae, iaculispurus. Donecfermentumporttitormollis. Mauris cursus fringilla ex, egetullamcorper ipsum lacinia in. Nam egetvehicula dui. In egetturpisconvallis, ultricesneque vitae, interdumturpis. Nullam non aliquamsapien, egetvolutpatelit. Cras pharetra ex a orcifaucibustristique at ullamcorpernibh. Proinneclacinia ante, eurutrum sem. Curabitur id libero purus. Vivamusvelvelitturpis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec at urnaegetaugueefficiturporttitor at euelit. Fuscefeugiattempornulla, at euismodlacustincidunt sed. Curabiturullamcorperdignissimnisl, egetiaculisorcivestibulum sed. Utconsecteturconsectetururnavestibulumultricies. Maecenas non felisarcu. Fusce in tortormetus. Vestibulumvelfelisut lorem ultriciespretiumquisutmetus. Aliquameratvolutpat. Praesent a lorem porttitor, venenatisnislvolutpat, placerat dui. Vivamusutjustoeuorcitinciduntmalesuada. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiamfacilisisnullavelleopretiumvarius. Fusceeleifendtinciduntlacinia. Duis maximus, sapien ac fringillapretium, augueleoaliquam ligula, quisrutrumleosemvel magna. Duis commodolobortis dui, utrhoncusdolor. Class aptenttacitisociosquadlitoratorquent per conubia nostra, per inceptoshimenaeos. Nam necaugueaugue. Quisqueeuorciarcu. Aliquamnequeodio, eleifend a dolor sed, dapibusauctorjusto. Aliquamsollicitudinarcu sit ametodio gravida, necviverranullaefficitur. Phasellus sed libero rutrumlacussollicitudinmattis. Sed fermentumsapien ac dolorelementum, quisvehiculasem tempus. Etiam et orci non orcilobortis dictum id vitae massa. Aeneaneueratnulla. Sed posuereullamcorper magna, temporultricesjustofeugiatrhoncus. Cras fringilla ligula neceuismodtristique. Duis vitae enimegetaugueconsecteturultricies. Nam laoreetsapien at dictum consectetur. Suspendissetristiquepurusneque, utblanditnunctincidunt et. Duis pretiumcondimentumdiam id viverra. Pellentesque sit ametdapibuseros, ac auctorlectus. Praesentegettelluspurus. Proinvelnisl sit ametorcilaoreetfaucibusegeteu nisi. Nulla id pharetra arcu. Lorem ipsum dolor sit amet, consecteturadipiscingelit. Vivamusornarelectuseumetusvenenatis, quisporttitornibh convallis. Nullanuncmetus, tristiquequis dui sed, interdumimperdietnisl. Vestibulummattistinciduntlacus, imperdietmattis libero variusrhoncus. Nam in auctornisl. Nunc tinciduntaccumsan pulvinar. Class aptenttacitisociosquadlitoratorquent per conubia nostra, per inceptoshimenaeos. Maurisluctusscelerisqueaugue, velfinibus ligula semper vel. Lorem ipsum dolor sit amet, consecteturadipiscingelit. Class aptenttacitisociosquadlitoratorquent per conubia nostra, per inceptoshimenaeos. Nunc ultriciesvelnuncegetmollis. Donec ligula felis, ultricesvelblanditut, hendreritvelturpis. Duis faucibusdapibus mi ac semper. Duis id tortor tempus augueeuismod tempus. Integer vehiculavelitutleoblanditsagittis. Vestibulum ante ipsum primis in faucibusorciluctus et ultricesposuerecubiliaCurae; Pellentesque habitant morbitristiquesenectus et netus et malesuada fames ac turpisegestas. Nunc uturnavelmetusmolestievenenatisnec non dui. Integer diammetus, aliquam a gravida et, varius id nulla. Nunc non porttitor ipsum. Aliquamsapienenim, eleifendnecnunc id, tempus tempus ex. Vivamusnecurnaornare, finibusleo at, posuereurna. Aeneanest mi, porta ac gravida at, hendreritquiselit. Quisqueurnamauris, lobortis sit amettortoreget, laoreetconsecteturtortor. Suspendisse id imperdietnisl, egetpellentesquetortor. Maecenas sit amet mi et ex ornare porta sollicitudin vitae tellus. Donecnulla lorem, imperdiet non sodales vitae, conguequissapien. Quisquenecmattislacus. Sed dapibus nisi nec libero ornare, in accumsandolorporttitor. Praesentsodalescommodoultricies. Pellentesque habitant morbitristiquesenectus et netus et malesuada fames ac turpisegestas. Cras id ipsum vestibulum, venenatiseros vitae, maximus magna. Mauriseublandittortor, condimentumeleifenddolor. Cras eutellusfeugiat, lobortismetus ac, consecteturorci. Phasellusbibendumtinciduntmassa non venenatis. Nunc sed molestiemetus, velelementumtortor. Duis malesuada porta nisl ac molestie. In a tellusfaucibus, convallis nuncnec, sodaleslacus. Donecvulputateinterdummassa sed posuere. In dapibuseu ligula at sodales. Sed facilisis a semegetlobortis. Utviverra ipsum dictum pharetra auctor. Duis tinciduntnullasapien, sit ametfacilisis ante rhoncuseu. Aliquamluctusdolortortor, vitae interdumfeliselementumeget. Nam mattisleo gravida ex elementum, id facilisislacusornare.");
}
