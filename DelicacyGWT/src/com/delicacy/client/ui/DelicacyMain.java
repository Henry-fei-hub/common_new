package com.delicacy.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.SideNavTree;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.ExplorerTreeNode;
import com.delicacy.client.data.HttpCookie;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.Print;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.prefetch.Prefetcher;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.NavigationDirection;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;

/**
 *
 * @author Peter
 */
public class DelicacyMain implements EntryPoint, SmartGWTMainEntry {

    protected boolean needLogin = true;
    private static final Logger __LOGGER = Logger.getLogger("");

    @Override
    public void onModuleLoad() {
        commonInit();
        commonLoginProcess();
    }

    public void initComponents() {

    }

    public void commonLoginProcess() {

        // 获取Local Storage
        HttpCookie cookies = new HttpCookie();
        Storage st = Storage.getLocalStorageIfSupported();
        if (st != null && cookies.getOperatorName() != null) {
            String expireTimeString = st.getItem(ClientUtil.LAST_USED_TIME);
            if (expireTimeString != null) {
                long expireTime = Long.parseLong(expireTimeString);
                if (expireTime > System.currentTimeMillis()) {
                    st.setItem(ClientUtil.LAST_USED_TIME, String.valueOf(System.currentTimeMillis() + ClientUtil.DAYTIME));
                    needLogin = false;
                }
            }
        }
//        needLogin = true;

//        loginWindow = new LoginWindow("");
//        loginWindow.setEntry(this);
//        if (needLogin) {
//            loginWindow.show();
//            loginWindow.bringToFront();
//        } else {
//            drawMainLayout();
//        }
    }

    public void commonInit() {
        // 设置字体的增加数
        Canvas.resizeFonts(3);
        // 设置控件的变大数，这是SmartGWT6新增的特性
        Canvas.resizeControls(6);
        // 初始化打印用的内部Frame
        Print.initPrint();
        // 初始化上传文件所用内部Frame
        Print.initUploadFrame();
        // 设置存储所有下拉数据的Local Strorage名字，大部分在前台页面上的下拉框里的数据都是静态的，基本上不会经常变动，系统会把这部分数据在应用启动时
        // 自动下载到浏览器，并利用HTML5的local storage机制存储在浏览器中，需要用时，只需要通过KeyValueManager通过指定名字取出即可使用
        // 对于那些需要实时从后台数据库读取的下拉框的数据，也是通过KeyValueManager通过指定名字取出并绑定到指定的控件上
        KeyValueManager.setDOMAINVALUE("DomainValue_JD");
        // 设置后台服务的名称，该名称主要用于构建测试环境下服务端访问的URL,构建规则如下：
        // KeyValueManager
        DBDataSource.setServerName("");
        // 加载下拉列表数据
//        __LOGGER.info("++++++++++++++++++++++++++++++++++++++++++");
        KeyValueManager.load();
        
//        KeyValueManager.getValueMapFromQuery("processExecutors", "Msystemprocesssor");

        // Always prefetch.
        Prefetcher.start();

        initToken = History.getToken();

        DateUtil.setShortDatetimeDisplayFormatter(new DateDisplayFormatter() {
            @Override
            public String format(Date date) {
                if (date == null) {
                    return null;
                }
                DateTimeFormat dateFormatter = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
                String format = dateFormatter.format(date);
                return format;
            }
        });
        DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
            @Override
            public String format(Date date) {
                if (date == null) {
                    return null;
                }
                DateTimeFormat dateFormatter = DateTimeFormat.getFormat("yyyy-MM-dd");
                String format = dateFormatter.format(date);
                return format;
            }
        });
    }

    @Override
    public void drawMainLayout() {

        // 主布局
        mainLayout = new VLayout() {
            @Override
            protected void onInit() {
                super.onInit();
                if (initToken.length() != 0) {
                    onHistoryChanged(initToken);
                }
            }
        };
        // 设置主布局的样式
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        mainLayout.setStyleName("tabSetContainer");
        // 设置可变显示区域的样式，在桌面环境下，左边显示菜单
        // 右边是一个tab面板
        // 在移动环境下，可自动转换成移动设备的显示方式
        splitPane.setBackgroundImage("backgrounds/emboss.jpg");
        splitPane.setBackgroundRepeat(BackgroundRepeat.REPEAT);
        splitPane.setWidth100();
        splitPane.setHeight100();
        // 如果是桌面系统，则不显示移动设备上方的导航条
        // 否则，则设置移动设备上方的默认显示为系统名称
        if (Browser.getIsDesktop()) {
            splitPane.setShowNavigationBar(false);
        } else {
            splitPane.setNavigationTitle(applicationName);
        }
        // 菜单显示区域的母版
        // 定义了固定的宽度
        VLayout sideNavLayout = new VLayout();
        sideNavLayout.setHeight100();
        sideNavLayout.setWidth(160);
        splitPane.setShowResizeBar(true);
        // 初始化左边菜单树面板
        
        
        
        
        sideNav = new SideNavTree();
        sideNav.setMenuData();
        sideNav.addLeafClickHandler(new LeafClickHandler() {
            // 当点击的是叶子节点时，则认为是要执行一项功能
            @Override
            public void onLeafClick(LeafClickEvent event) {
                TreeNode node = event.getLeaf();
                functionProcess(node);
            }
        });
        
        // 把菜单树放入左边的布局中
        sideNavLayout.addMember(sideNav);
        // 将可分割面板的导航区域设置为左边的菜单树母版
        splitPane.setNavigationPane(sideNavLayout);
        // 初始化功能显示区
        mainTabSet = new TabSet();
        // 设置功能显示区域的Tab为不能关闭
        mainTabSet.setCanCloseTabs(Boolean.FALSE);

        Layout paneContainerProperties = new Layout();
        paneContainerProperties.setLayoutMargin(0);
        paneContainerProperties.setLayoutTopMargin(1);
        // 设置功能显示区域的样式
        mainTabSet.setPaneContainerProperties(paneContainerProperties);
        // 设置功能显示区的样式
        mainTabSet.setWidth100();
        mainTabSet.setHeight100();
        // 添加功能显示区域的改变功能处理程序
        mainTabSet.addTabSelectedHandler(new TabSelectedHandler() {
            @Override
            public void onTabSelected(TabSelectedEvent event) {
                Tab selectedTab = event.getTab();
                // 每个功能都有一个唯一历史标识码
                // 取出该功能的浏览历史标识码
                String historyToken = selectedTab.getAttribute("historyToken");
                // 如果浏览历史标识码为空，说明是默认主页
                if (historyToken != null) {
                    History.newItem(historyToken, false);
                } else {
                    History.newItem("main", false);
                }
            }
        });

        LayoutSpacer layoutSpacer = new LayoutSpacer();
        layoutSpacer.setWidth(5);
        // 设置功能显示分栏区域的控制按钮，有tab的滚动按钮，有tab的选取按钮还有一个5px宽的空白区域
        mainTabSet.setTabBarControls(TabBarControls.TAB_SCROLLER, TabBarControls.TAB_PICKER, layoutSpacer);
        // 创建每个tab的下拉菜单项，有关闭当前，关闭其他和关闭所有
        contextMenu = createContextMenu();
        // 创建一个默认的主tab，作为当前用户的主页
        Tab tab = new Tab();
        tab.setTitle("Home&nbsp;&nbsp;");
        tab.setIcon("pieces/16/cube_green.png", 16);
        // 用来承载用户默认主页的面板
        mainPanel = new VLayout();
        mainPanel.setHeight100();
        mainPanel.setWidth100();

        mainPanel.setAlign(Alignment.CENTER);
        mainPanel.setAlign(VerticalAlignment.CENTER);
        mainPanel.setPadding(15);
        // 可以在子类中重写该方法以实现用户主页的初始化，并把主页内容放入mainPanel中
        initComponents();
        // 把主页面板放入tab
        tab.setPane(mainPanel);
        tab.setCanClose(false);

        mainTabSet.addTab(tab);
        // 如果是移动设备，我们希望在导航页面下面显示工具栏，一般包括修改密码，重新登录等功能按钮
        // 如果是桌面系统，我们会把这些按钮放置在功能显示容器的TabBarControls中
        ToolStrip tools = generateTopStrip();
        if (Browser.getIsTouch()) {
            sideNavLayout.addMember(tools);
        }
        // 初始化功能显示容器的母版，并将功能显示容器放入其中
        Canvas canvas = new Canvas();
        canvas.setBackgroundImage("[SKIN]/shared/background.gif");
        canvas.setWidth100();
        canvas.setHeight100();
        canvas.addChild(mainTabSet);
        // 将功能显示容器的母版放入可拆分容器的明细面板
        splitPane.setShowDetailToolStrip(false);
        splitPane.setDetailPane(canvas);

        mainLayout.addMember(splitPane);

        mainLayout.draw();

    }

    private ToolStrip generateTopStrip() {

        List<Canvas> detailTools = new ArrayList<>();

        ToolStrip topBar = new ToolStrip();
        topBar.setHeight(50);
        topBar.setWidth100();
        topBar.setBackgroundColor("#F2F2F2");
        topBar.setDefaultLayoutAlign(VerticalAlignment.CENTER);

        topBar.addSpacer(6);

        StringBuilder sb = new StringBuilder();
        if(ClientUtil.getDutyId() == -1){//说明未设定职位,默认为普通职员
        	sb.append("普通职员");
        }else{
            sb.append(ClientUtil.getDutyName());
        }
        sb.append(" ").append(ClientUtil.getUserName());
        Label roleAndUserName = new Label(sb.toString());
        roleAndUserName.setWidth(200);
        roleAndUserName.setAlign(Alignment.RIGHT);
        roleAndUserName.setMargin(5);
        detailTools.add(roleAndUserName);
        ImgButton sgwtHomeButton = new ImgButton();
        sgwtHomeButton.setSrc("logo.png");
        sgwtHomeButton.setWidth(45);
        sgwtHomeButton.setHeight(24);
        sgwtHomeButton.setShowRollOver(false);
        sgwtHomeButton.setShowDownIcon(false);
        sgwtHomeButton.setShowDown(false);
        topBar.addMember(sgwtHomeButton);
        topBar.addSpacer(6);

        detailTools.add(sgwtHomeButton);
        detailTools.add(new LayoutSpacer(5, 1));

        ImgButton modifyPwd = new ImgButton();
        modifyPwd.setSrc("changepwd.png");
        modifyPwd.setWidth(24);
        modifyPwd.setHeight(24);
        modifyPwd.setShowRollOver(false);
        modifyPwd.setShowDownIcon(false);
        modifyPwd.setShowDown(false);
        modifyPwd.setPrompt("Change password");
        modifyPwd.setShowHover(true);
        modifyPwd.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                PopupWindow popupWindow = new PopupWindow("Change password");
                popupWindow.setWidth(450);
                popupWindow.setHeight(400);
                popupWindow.centerInPage();
                UserModifyPwd user = new UserModifyPwd();
                user.initComponents();
                popupWindow.addItem(user);
                user.setParentWindow(popupWindow);
                popupWindow.show();
            }
        });
        detailTools.add(modifyPwd);
        detailButtons.add(modifyPwd);
        detailTools.add(new LayoutSpacer(5, 1));
        topBar.addMember(modifyPwd);
        topBar.addSpacer(16);

        ImgButton quitBtn = new ImgButton();
        quitBtn.setSrc("logout.png");
        quitBtn.setWidth(24);
        quitBtn.setHeight(24);
        quitBtn.setShowRollOver(false);
        quitBtn.setShowDownIcon(false);
        quitBtn.setShowDown(false);
        quitBtn.setPrompt("relogin");
        quitBtn.setShowHover(true);
        quitBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	systemLogout();
            }
        });
        topBar.addMember(quitBtn);
        topBar.addSpacer(16);
        detailTools.add(quitBtn);
        detailButtons.add(quitBtn);
        detailTools.add(new LayoutSpacer(5, 1));

        if (Browser.getIsDesktop()) {
            mainTabSet.setTabBarControls(detailTools.toArray(new Object[detailTools.size()]));
        }

        return topBar;
    }

    protected Menu createContextMenu() {
        Menu menu = new Menu();
        menu.setWidth(140);

        MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                int selectedTab = mainTabSet.getSelectedTabNumber();
                return selectedTab != 0;
            }
        };

        MenuItem closeItem = new MenuItem("Close");
        closeItem.setEnableIfCondition(enableCondition);
        closeItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                int selectedTab = mainTabSet.getSelectedTabNumber();
                mainTabSet.removeTab(selectedTab);
                mainTabSet.selectTab(selectedTab - 1);
            }
        });

        MenuItem closeAllButCurrent = new MenuItem("Close Others");
        closeAllButCurrent.setEnableIfCondition(enableCondition);
        closeAllButCurrent.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                int selected = mainTabSet.getSelectedTabNumber();
                Tab[] tabs = mainTabSet.getTabs();
                int[] tabsToRemove = new int[tabs.length - 2];
                int cnt = 0;
                for (int i = 1; i < tabs.length; i++) {
                    if (i != selected) {
                        tabsToRemove[cnt] = i;
                        cnt++;
                    }
                }
                mainTabSet.removeTabs(tabsToRemove);
            }
        });

        MenuItem closeAll = new MenuItem("Close All");
        closeAll.setEnableIfCondition(enableCondition);
        closeAll.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Tab[] tabs = mainTabSet.getTabs();
                int[] tabsToRemove = new int[tabs.length - 1];

                for (int i = 1; i < tabs.length; i++) {
                    tabsToRemove[i - 1] = i;
                }
                mainTabSet.removeTabs(tabsToRemove);
                mainTabSet.selectTab(0);
            }
        });

        menu.setItems(closeItem, closeAllButCurrent, closeAll);
        return menu;
    }

    public void onHistoryChanged(String historyToken) {
        if (historyToken == null || historyToken.equals("")) {
            mainTabSet.selectTab(0);
        } else {
            ExplorerTreeNode[] showcaseData = sideNav.getMenuData();// .getmaterialMenuData();
            for (ExplorerTreeNode explorerTreeNode : showcaseData) {
                if (explorerTreeNode.getNodeID().equals(historyToken)) {
                    functionProcess(explorerTreeNode);
                    ListGridRecord selectedRecord = sideNav.getSelectedRecord();
                    if (selectedRecord != null) {
                        sideNav.deselectRecord(selectedRecord);
                    }
                    sideNav.selectRecord(explorerTreeNode);
                    Tree tree = sideNav.getData();
                    TreeNode categoryNode = tree.getParent(explorerTreeNode);
                    while (categoryNode != null && !"/".equals(tree.getName(categoryNode))) {
                        tree.openFolder(categoryNode);
                        categoryNode = tree.getParent(categoryNode);
                    }
                }
            }
        }
    }

    public void functionProcess(final TreeNode node) {

        boolean isExplorerTreeNode = node instanceof ExplorerTreeNode;
        if (isExplorerTreeNode) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(Throwable reason) {
                    SC.say("Failure to download code");
                }

                @Override
                public void onSuccess() {
                    displayFunctionPane(node);
                }
            });
        }
    }

    protected void displayFunctionPane(TreeNode node) {
        final ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode) node;
        PanelFactory factory = explorerTreeNode.getFactory();
        if (factory == null) {
            return;
        }
        if (Browser.getIsDesktop()) {
            String panelID = factory.getID();
            Tab tab = null;
            if (panelID != null) {
                String tabID = panelID + "_tab";
                tab = mainTabSet.getTab(tabID);
            }
            if (tab == null) {
                Canvas panel = factory.create();
                tab = new Tab();
                tab.setID(factory.getID() + "_tab");
                // store history token on tab so that
                // when an already open
                // is selected, one can retrieve the
                // history token and update the URL
                tab.setAttribute("historyToken", explorerTreeNode.getNodeID());
                tab.setContextMenu(contextMenu);

                String functionName = explorerTreeNode.getName();

                String icon = explorerTreeNode.getIcon();
                if (icon == null) {
                    icon = "silk/plugin.png";
                }
                String imgHTML = Canvas.imgHTML(icon, 16, 16);
                tab.setTitle("<span>" + imgHTML + "&nbsp;" + functionName + "</span>");
                tab.setPane(panel);
                tab.setCanClose(true);
                mainTabSet.addTab(tab);
                mainTabSet.selectTab(tab);
            } else {
                mainTabSet.selectTab(tab);
            }
        } else {
            final Canvas oldDetailPane = splitPane.getDetailPane();
            Canvas panel = factory.create();
            splitPane.setDetailPane(panel);
            if (oldDetailPane != null) {
                oldDetailPane.destroy();
            }
            splitPane.showDetailPane(node.getName(), "System", NavigationDirection.FORWARD);
        }
    }

    protected static final Logger __logger = Logger.getLogger("");
    protected String applicationName;
    protected TabSet mainTabSet;
    protected SideNavTree sideNav;
    protected Menu contextMenu;
    protected static VLayout mainLayout;
    protected String initToken;
    protected String employeeName;
    protected Label welcome;
//    protected static LoginWindow loginWindow;
    protected VLayout mainPanel;
    protected SplitPane splitPane = new SplitPane();
    protected List<Canvas> detailButtons = new ArrayList<>();
    protected HLayout navLayout = new HLayout(10);

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * @return the mainTabSet
     */
    public TabSet getMainTabSet() {
        return mainTabSet;
    }

    /**
     * @param mainTabSet the mainTabSet to set
     */
    public void setMainTabSet(TabSet mainTabSet) {
        this.mainTabSet = mainTabSet;
    }

    /**
     * @return the sideNav
     */
    public SideNavTree getSideNav() {
        return sideNav;
    }

    /**
     * @param sideNav the sideNav to set
     */
    public void setSideNav(SideNavTree sideNav) {
        this.sideNav = sideNav;
    }

    /**
     * @return the contextMenu
     */
    public Menu getContextMenu() {
        return contextMenu;
    }

    /**
     * @param contextMenu the contextMenu to set
     */
    public void setContextMenu(Menu contextMenu) {
        this.contextMenu = contextMenu;
    }

    /**
     * @return the mainLayout
     */
    public VLayout getMainLayout() {
        return mainLayout;
    }

    /**
     * @param mainLayout the mainLayout to set
     */
    public void setMainLayout(VLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    /**
     * @return the initToken
     */
    public String getInitToken() {
        return initToken;
    }

    /**
     * @param initToken the initToken to set
     */
    public void setInitToken(String initToken) {
        this.initToken = initToken;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the welcome
     */
    public Label getWelcome() {
        return welcome;
    }

    /**
     * @param welcome the welcome to set
     */
    public void setWelcome(Label welcome) {
        this.welcome = welcome;
    }

    /**
     * @return the loginWindow
     */
//    public LoginWindow getLoginWindow() {
//        return loginWindow;
//    }

    /**
     * @param loginWindow the loginWindow to set
     */
//    public void setLoginWindow(LoginWindow loginWindow) {
//        this.loginWindow = loginWindow;
//    }

    /**
     * @return the mainPanel
     */
    public VLayout getMainPanel() {
        return mainPanel;
    }

    /**
     * @param mainPanel the mainPanel to set
     */
    public void setMainPanel(VLayout mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * @return the splitPane
     */
    public SplitPane getSplitPane() {
        return splitPane;
    }

    /**
     * @param splitPane the splitPane to set
     */
    public void setSplitPane(SplitPane splitPane) {
        this.splitPane = splitPane;
    }

    /**
     * @return the detailButtons
     */
    public List<Canvas> getDetailButtons() {
        return detailButtons;
    }

    /**
     * @param detailButtons the detailButtons to set
     */
    public void setDetailButtons(List<Canvas> detailButtons) {
        this.detailButtons = detailButtons;
    }

    /**
     * @return the navLayout
     */
    public HLayout getNavLayout() {
        return navLayout;
    }

    /**
     * @param navLayout the navLayout to set
     */
    public void setNavLayout(HLayout navLayout) {
        this.navLayout = navLayout;
    }
    
    public static void systemLogout(){
//    	KeyValueManager.load();
    	if(null != mainLayout) {
    		mainLayout.destroy();
    	}
//        loginWindow.removeCookies();
//        loginWindow.show();
//        loginWindow.bringToFront();
    }
}
