
package com.gameco.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for user complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="user"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="avaibleGame" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="callback" type="{http://server.gameco.com/}callback" minOccurs="0"/&gt;
 *         &lt;element name="game" type="{http://server.gameco.com/}gameContainer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "username",
    "sessionID",
    "avaibleGame",
    "callback",
    "game"
})
public class User {

    protected String username;
    protected String sessionID;
    @XmlElement(nillable = true)
    protected List<String> avaibleGame;
    protected Callback callback;
    protected GameContainer game;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the avaibleGame property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the avaibleGame property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvaibleGame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAvaibleGame() {
        if (avaibleGame == null) {
            avaibleGame = new ArrayList<String>();
        }
        return this.avaibleGame;
    }

    /**
     * Gets the value of the callback property.
     * 
     * @return
     *     possible object is
     *     {@link Callback }
     *     
     */
    public Callback getCallback() {
        return callback;
    }

    /**
     * Sets the value of the callback property.
     * 
     * @param value
     *     allowed object is
     *     {@link Callback }
     *     
     */
    public void setCallback(Callback value) {
        this.callback = value;
    }

    /**
     * Gets the value of the game property.
     * 
     * @return
     *     possible object is
     *     {@link GameContainer }
     *     
     */
    public GameContainer getGame() {
        return game;
    }

    /**
     * Sets the value of the game property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameContainer }
     *     
     */
    public void setGame(GameContainer value) {
        this.game = value;
    }

}
