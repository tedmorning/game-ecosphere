
package com.gameco.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gameco.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Accept_QNAME = new QName("http://server.gameco.com/", "accept");
    private final static QName _AcceptResponse_QNAME = new QName("http://server.gameco.com/", "acceptResponse");
    private final static QName _AddCallback_QNAME = new QName("http://server.gameco.com/", "addCallback");
    private final static QName _AddCallbackResponse_QNAME = new QName("http://server.gameco.com/", "addCallbackResponse");
    private final static QName _Callback_QNAME = new QName("http://server.gameco.com/", "callback");
    private final static QName _Cancel_QNAME = new QName("http://server.gameco.com/", "cancel");
    private final static QName _CancelResponse_QNAME = new QName("http://server.gameco.com/", "cancelResponse");
    private final static QName _GameContainer_QNAME = new QName("http://server.gameco.com/", "gameContainer");
    private final static QName _GameHistory_QNAME = new QName("http://server.gameco.com/", "gameHistory");
    private final static QName _GameMove_QNAME = new QName("http://server.gameco.com/", "gameMove");
    private final static QName _GetAvaibleGames_QNAME = new QName("http://server.gameco.com/", "getAvaibleGames");
    private final static QName _GetAvaibleGamesResponse_QNAME = new QName("http://server.gameco.com/", "getAvaibleGamesResponse");
    private final static QName _GetGames_QNAME = new QName("http://server.gameco.com/", "getGames");
    private final static QName _GetGamesResponse_QNAME = new QName("http://server.gameco.com/", "getGamesResponse");
    private final static QName _GetUsers_QNAME = new QName("http://server.gameco.com/", "getUsers");
    private final static QName _GetUsersResponse_QNAME = new QName("http://server.gameco.com/", "getUsersResponse");
    private final static QName _Invite_QNAME = new QName("http://server.gameco.com/", "invite");
    private final static QName _InviteResponse_QNAME = new QName("http://server.gameco.com/", "inviteResponse");
    private final static QName _Join_QNAME = new QName("http://server.gameco.com/", "join");
    private final static QName _JoinResponse_QNAME = new QName("http://server.gameco.com/", "joinResponse");
    private final static QName _Login_QNAME = new QName("http://server.gameco.com/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://server.gameco.com/", "loginResponse");
    private final static QName _Logout_QNAME = new QName("http://server.gameco.com/", "logout");
    private final static QName _LogoutResponse_QNAME = new QName("http://server.gameco.com/", "logoutResponse");
    private final static QName _PlayingGame_QNAME = new QName("http://server.gameco.com/", "playingGame");
    private final static QName _SetMove_QNAME = new QName("http://server.gameco.com/", "setMove");
    private final static QName _SetMoveResponse_QNAME = new QName("http://server.gameco.com/", "setMoveResponse");
    private final static QName _SetMoves_QNAME = new QName("http://server.gameco.com/", "setMoves");
    private final static QName _SetMovesResponse_QNAME = new QName("http://server.gameco.com/", "setMovesResponse");
    private final static QName _User_QNAME = new QName("http://server.gameco.com/", "user");
    private final static QName _CredentialException_QNAME = new QName("http://server.gameco.com/", "CredentialException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gameco.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Accept }
     * 
     */
    public Accept createAccept() {
        return new Accept();
    }

    /**
     * Create an instance of {@link AcceptResponse }
     * 
     */
    public AcceptResponse createAcceptResponse() {
        return new AcceptResponse();
    }

    /**
     * Create an instance of {@link AddCallback }
     * 
     */
    public AddCallback createAddCallback() {
        return new AddCallback();
    }

    /**
     * Create an instance of {@link AddCallbackResponse }
     * 
     */
    public AddCallbackResponse createAddCallbackResponse() {
        return new AddCallbackResponse();
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link CancelResponse }
     * 
     */
    public CancelResponse createCancelResponse() {
        return new CancelResponse();
    }

    /**
     * Create an instance of {@link GameHistory }
     * 
     */
    public GameHistory createGameHistory() {
        return new GameHistory();
    }

    /**
     * Create an instance of {@link GameMove }
     * 
     */
    public GameMove createGameMove() {
        return new GameMove();
    }

    /**
     * Create an instance of {@link GetAvaibleGames }
     * 
     */
    public GetAvaibleGames createGetAvaibleGames() {
        return new GetAvaibleGames();
    }

    /**
     * Create an instance of {@link GetAvaibleGamesResponse }
     * 
     */
    public GetAvaibleGamesResponse createGetAvaibleGamesResponse() {
        return new GetAvaibleGamesResponse();
    }

    /**
     * Create an instance of {@link GetGames }
     * 
     */
    public GetGames createGetGames() {
        return new GetGames();
    }

    /**
     * Create an instance of {@link GetGamesResponse }
     * 
     */
    public GetGamesResponse createGetGamesResponse() {
        return new GetGamesResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link Invite }
     * 
     */
    public Invite createInvite() {
        return new Invite();
    }

    /**
     * Create an instance of {@link InviteResponse }
     * 
     */
    public InviteResponse createInviteResponse() {
        return new InviteResponse();
    }

    /**
     * Create an instance of {@link Join }
     * 
     */
    public Join createJoin() {
        return new Join();
    }

    /**
     * Create an instance of {@link JoinResponse }
     * 
     */
    public JoinResponse createJoinResponse() {
        return new JoinResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link PlayingGame }
     * 
     */
    public PlayingGame createPlayingGame() {
        return new PlayingGame();
    }

    /**
     * Create an instance of {@link SetMove }
     * 
     */
    public SetMove createSetMove() {
        return new SetMove();
    }

    /**
     * Create an instance of {@link SetMoveResponse }
     * 
     */
    public SetMoveResponse createSetMoveResponse() {
        return new SetMoveResponse();
    }

    /**
     * Create an instance of {@link SetMoves }
     * 
     */
    public SetMoves createSetMoves() {
        return new SetMoves();
    }

    /**
     * Create an instance of {@link SetMovesResponse }
     * 
     */
    public SetMovesResponse createSetMovesResponse() {
        return new SetMovesResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link CredentialException }
     * 
     */
    public CredentialException createCredentialException() {
        return new CredentialException();
    }

    /**
     * Create an instance of {@link Duration }
     * 
     */
    public Duration createDuration() {
        return new Duration();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Accept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "accept")
    public JAXBElement<Accept> createAccept(Accept value) {
        return new JAXBElement<Accept>(_Accept_QNAME, Accept.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "acceptResponse")
    public JAXBElement<AcceptResponse> createAcceptResponse(AcceptResponse value) {
        return new JAXBElement<AcceptResponse>(_AcceptResponse_QNAME, AcceptResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCallback }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "addCallback")
    public JAXBElement<AddCallback> createAddCallback(AddCallback value) {
        return new JAXBElement<AddCallback>(_AddCallback_QNAME, AddCallback.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCallbackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "addCallbackResponse")
    public JAXBElement<AddCallbackResponse> createAddCallbackResponse(AddCallbackResponse value) {
        return new JAXBElement<AddCallbackResponse>(_AddCallbackResponse_QNAME, AddCallbackResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Callback }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "callback")
    public JAXBElement<Callback> createCallback(Callback value) {
        return new JAXBElement<Callback>(_Callback_QNAME, Callback.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "cancel")
    public JAXBElement<Cancel> createCancel(Cancel value) {
        return new JAXBElement<Cancel>(_Cancel_QNAME, Cancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "cancelResponse")
    public JAXBElement<CancelResponse> createCancelResponse(CancelResponse value) {
        return new JAXBElement<CancelResponse>(_CancelResponse_QNAME, CancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "gameContainer")
    public JAXBElement<GameContainer> createGameContainer(GameContainer value) {
        return new JAXBElement<GameContainer>(_GameContainer_QNAME, GameContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "gameHistory")
    public JAXBElement<GameHistory> createGameHistory(GameHistory value) {
        return new JAXBElement<GameHistory>(_GameHistory_QNAME, GameHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameMove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "gameMove")
    public JAXBElement<GameMove> createGameMove(GameMove value) {
        return new JAXBElement<GameMove>(_GameMove_QNAME, GameMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvaibleGames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "getAvaibleGames")
    public JAXBElement<GetAvaibleGames> createGetAvaibleGames(GetAvaibleGames value) {
        return new JAXBElement<GetAvaibleGames>(_GetAvaibleGames_QNAME, GetAvaibleGames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvaibleGamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "getAvaibleGamesResponse")
    public JAXBElement<GetAvaibleGamesResponse> createGetAvaibleGamesResponse(GetAvaibleGamesResponse value) {
        return new JAXBElement<GetAvaibleGamesResponse>(_GetAvaibleGamesResponse_QNAME, GetAvaibleGamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "getGames")
    public JAXBElement<GetGames> createGetGames(GetGames value) {
        return new JAXBElement<GetGames>(_GetGames_QNAME, GetGames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "getGamesResponse")
    public JAXBElement<GetGamesResponse> createGetGamesResponse(GetGamesResponse value) {
        return new JAXBElement<GetGamesResponse>(_GetGamesResponse_QNAME, GetGamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Invite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "invite")
    public JAXBElement<Invite> createInvite(Invite value) {
        return new JAXBElement<Invite>(_Invite_QNAME, Invite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InviteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "inviteResponse")
    public JAXBElement<InviteResponse> createInviteResponse(InviteResponse value) {
        return new JAXBElement<InviteResponse>(_InviteResponse_QNAME, InviteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Join }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "join")
    public JAXBElement<Join> createJoin(Join value) {
        return new JAXBElement<Join>(_Join_QNAME, Join.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "joinResponse")
    public JAXBElement<JoinResponse> createJoinResponse(JoinResponse value) {
        return new JAXBElement<JoinResponse>(_JoinResponse_QNAME, JoinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayingGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "playingGame")
    public JAXBElement<PlayingGame> createPlayingGame(PlayingGame value) {
        return new JAXBElement<PlayingGame>(_PlayingGame_QNAME, PlayingGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "setMove")
    public JAXBElement<SetMove> createSetMove(SetMove value) {
        return new JAXBElement<SetMove>(_SetMove_QNAME, SetMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMoveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "setMoveResponse")
    public JAXBElement<SetMoveResponse> createSetMoveResponse(SetMoveResponse value) {
        return new JAXBElement<SetMoveResponse>(_SetMoveResponse_QNAME, SetMoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMoves }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "setMoves")
    public JAXBElement<SetMoves> createSetMoves(SetMoves value) {
        return new JAXBElement<SetMoves>(_SetMoves_QNAME, SetMoves.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMovesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "setMovesResponse")
    public JAXBElement<SetMovesResponse> createSetMovesResponse(SetMovesResponse value) {
        return new JAXBElement<SetMovesResponse>(_SetMovesResponse_QNAME, SetMovesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CredentialException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.gameco.com/", name = "CredentialException")
    public JAXBElement<CredentialException> createCredentialException(CredentialException value) {
        return new JAXBElement<CredentialException>(_CredentialException_QNAME, CredentialException.class, null, value);
    }

}
