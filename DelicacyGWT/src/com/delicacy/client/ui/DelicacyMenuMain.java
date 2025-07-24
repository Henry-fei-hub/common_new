package com.delicacy.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
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
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.DateChooser;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 *
 * @author Peter
 */
public class DelicacyMenuMain implements EntryPoint, SmartGWTMainEntry {

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
        openLoginWindow();
        if (!needLogin) {
        	drawMainLayout();
        }
    }
    
    public void openLoginWindow(){
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
		
		Canvas monthChooserButtonTemplate = new Canvas();
		monthChooserButtonTemplate.setWidth(50); //随意调整宽度，使其看起来最好     
		DateChooser.changeAutoChildDefaults("monthChooserButton",monthChooserButtonTemplate);

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
    }

    public ToolStrip generateTopStrip() {
        ToolStrip topBar = new ToolStrip();
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

        MenuItem closeItem = new MenuItem("关闭");
        closeItem.setEnableIfCondition(enableCondition);
        closeItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                int selectedTab = mainTabSet.getSelectedTabNumber();
                mainTabSet.removeTab(selectedTab);
                mainTabSet.selectTab(selectedTab - 1);
            }
        });

        MenuItem closeAllButCurrent = new MenuItem("关闭其它");
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

        MenuItem closeAll = new MenuItem("关闭所有");
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
            ExplorerTreeNode[] showcaseData = sidemunu.getMenuData();// .getmaterialMenuData();
            for (ExplorerTreeNode explorerTreeNode : showcaseData) {
                if (explorerTreeNode.getNodeID().equals(historyToken)) {
                    functionProcess(explorerTreeNode);
                    ListGridRecord selectedRecord = sidemunu.getSelectedRecord();
                    if (selectedRecord != null) {
                    	sidemunu.deselectRecord(selectedRecord);
                    }
                    sidemunu.selectRecord(explorerTreeNode);
                    Tree tree = sidemunu.getTree();
                    TreeNode categoryNode = tree.getParent(explorerTreeNode);
                    while (categoryNode != null && !"/".equals(tree.getName(categoryNode))) {
                        tree.openFolder(categoryNode);
                        categoryNode = tree.getParent(categoryNode);
                    }
                }
            }
        }
    }

    public static void functionProcess(final TreeNode node) {
        final DelicacyMenuMain main = new DelicacyMenuMain();
    	//点击菜单的时候，先判断用户是否登录，如果没有，则关闭窗口
    	if(ClientUtil.getEmployeeId() < 0) {
            main.systemLogout();
    		return;
    	}
        boolean isExplorerTreeNode = node instanceof ExplorerTreeNode;
        if (isExplorerTreeNode) {
            GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(Throwable reason) {
                    SC.say("Failure to download code");
                }

                @Override
                public void onSuccess() {
                    main.displayFunctionPane(node);
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
//            final Canvas oldDetailPane = splitPane.getDetailPane();
//            Canvas panel = factory.create();
//            splitPane.setDetailPane(panel);
//            if (oldDetailPane != null) {
//                oldDetailPane.destroy();
//            }
//            splitPane.showDetailPane(node.getName(), "System", NavigationDirection.FORWARD);
        }
    }

    protected static final Logger __logger = Logger.getLogger("");
    protected String applicationName;
    public static TabSet mainTabSet;
    protected SideNavTree sideNav;
    protected SideMunuTree sidemunu;
    public static Menu contextMenu;
    public static VLayout mainLayout;
    protected String initToken;
    protected String employeeName;
    protected Label welcome;
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
    
    public void systemLogout(){
    	if(!BaseHelpUtils.isNullOrEmpty(mainLayout)) {
    		mainLayout.destroy();
    	}
    }
    
    public static boolean checkLogin() {
    	if(ClientUtil.getEmployeeId() < 0) {
    		if(!BaseHelpUtils.isNullOrEmpty(mainLayout)) {
    			mainLayout.destroy();
    			SC.say("提示","已掉线，请刷新登录……");
    			return false;
    		}
    	}
    	return true;
    }
}
