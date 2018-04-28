
package com.gameco.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for playingGame complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="playingGame"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="players" type="{http://server.gameco.com/}user" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playingGame", propOrder = {
    "gameID",
    "players"
})
public class PlayingGame {

    protected String gameID;
    @XmlElement(nillable = true)
    protected List<User> players;

    /**
     * Gets the value of the gameID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGameID() {
        return gameID;
    }

    /**
     * Sets the value of the gameID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGameID(String value) {
        this.gameID = value;
    }

    /**
     * Gets the value of the players property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the players property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlayers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getPlayers() {
        if (players == null) {
            players = new ArrayList<User>();
        }
        return this.players;
    }

}
