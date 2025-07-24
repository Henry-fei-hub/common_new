package com.delicacy.client.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.KeyValueManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ArrowStyle;
import com.smartgwt.client.types.LinePattern;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;

/**
 *
 * @author Peter Cao
 */
public class ShowPanelUtils {

	public final static String ACTIVITIES_NAME = "activities";
	public final static String ACTIVITITY_TYPE = "activityType";
	public final static String ACTIVITITY_NAME = "employeeId";
	public final static String ATTENTION_NAME = "attentions";
	public final static String TASK_NAME = "tasks";
	public final static String STATUS_NAME = "status";
	public final static String POOL_TYPE_NAME = "poolType";
	public final static String ACTIVITIES_NAME1 = "detailSystemProcessInstanceActivity";
	private static final Map<String, Record> processInstances = new HashMap<>();

	public static DrawPane generateProcessPane(Record processInstance) {
		final DrawPane draw = new DrawPane();
		draw.addResizedHandler(new ResizedHandler() {
			@Override
			public void onResized(ResizedEvent event) {
			}
		});
		Record[] activities = processInstance.getAttributeAsRecordArray(ACTIVITIES_NAME);
		if (activities == null || activities.length == 0) {
			activities = processInstance.getAttributeAsRecordArray(ACTIVITIES_NAME1);
			if (activities == null || activities.length == 0) {
				return draw;
			}
		}
		int width = draw.getInnerContentWidth();
		int height = draw.getInnerContentHeight();
		if (width == 0) {
			width = 1000;
			draw.setWidth(width);
		}
		if (height == 0) {
			height = 700;
			draw.setHeight(height);
		}
		int x = 10, y = 10;
		int count = 0;
		ProcessDrawNode preDrawNode = null;
		for (Record r : activities) {
			int activityType = r.getAttribute(ACTIVITITY_TYPE) == null ? 2 : r.getAttributeAsInt(ACTIVITITY_TYPE);
			int activityStatus = r.getAttributeAsInt(STATUS_NAME);
			String backViewName = r.getAttribute("backViewName");
			String activityName = r.getAttribute(ACTIVITITY_NAME);
			activityName = getEmployeeName(activityName, backViewName);
			ProcessDrawNode drawNode = generateProcessNode(x, y, activityType, activityName);
			drawNode.setHelpMessage(generateComment(r));
			drawNode.setDescription(getAppromentComment(activityType, activityStatus));
			if (activityStatus == 1) {
				drawNode.setActiveIcon();
			} else {
				drawNode.setNormalIcon();
			}
			draw.addDrawItem(drawNode, true);
			if (count > 0) {
				ProcessNodeConnector pnc = generateDrawPath(preDrawNode, drawNode);
				pnc.setCanDrag(false);
				draw.addDrawItem(pnc, true);
			}
			x += ProcessDrawNode.ICON_SIZE * 2;
			count++;
			preDrawNode = drawNode;
		}
		draw.setZoomLevel(0.75);
		return draw;
	}

	public static DrawPane generateDetailProcessPane(Record processInstance) {
		final DrawPane draw = new DrawPane();
		processInstances.put(draw.getID(), processInstance);
		draw.setWidth(1000);
		draw.setHeight(600);

		draw.addResizedHandler(new ResizedHandler() {

			@Override
			public void onResized(ResizedEvent event) {
				generateDetailProcessPane(draw);
			}
		});
		generateDetailProcessPane(draw);
		return draw;
	}

	public static DrawPane generateDetailProcessPane(final DrawPane draw) {
		Record processInstance = processInstances.get(draw.getID());
		if (processInstance == null)
			return draw;
		draw.destroyItems();
		Record[] activities = processInstance.getAttributeAsRecordArray(ACTIVITIES_NAME);
		if (activities == null || activities.length == 0) {
			activities = processInstance.getAttributeAsRecordArray(ACTIVITIES_NAME1);
			if (activities == null || activities.length == 0) {
				return draw;
			}
		}
		int width = draw.getInnerContentWidth();
		int height = draw.getInnerContentHeight();
		if (width == 0) {
			width = 1000;
			draw.setWidth(width);
		}
		if (height == 0) {
			height = 600;
			draw.setHeight(height);
		}
		int x = 10, y = 10;
		int count = 0;
		int direction = 0;
		ProcessDrawNode preDrawNode = null;
		boolean hasAttention = false;
		for (Record r : activities) {
			int activityType = r.getAttribute(ACTIVITITY_TYPE) == null ? 2 : r.getAttributeAsInt(ACTIVITITY_TYPE);
			int activityStatus = r.getAttributeAsInt(STATUS_NAME);
			String backViewName = r.getAttribute("backViewName");
			String activityName = r.getAttribute(ACTIVITITY_NAME);
			activityName = getEmployeeName(activityName, backViewName);
			ProcessDrawNode drawNode = generateProcessNode(x, y, activityType, activityName);
			drawNode.setHelpMessage(generateComment(r));
			drawNode.setDescription(getAppromentComment(activityType, activityStatus));
			if (activityStatus == 1) {
				drawNode.setActiveIcon();
			} else {
				drawNode.setNormalIcon();
			}
			draw.addDrawItem(drawNode, true);
			if (count > 0) {
				ProcessNodeConnector pnc = generateDrawPath(preDrawNode, drawNode);
				pnc.setCanDrag(false);
				draw.addDrawItem(pnc, true);
			}
			Record[] attentions = r.getAttributeAsRecordArray("attentions");
			if (attentions != null && attentions.length > 0) {
				int ax = 0, ay = y + ProcessDrawNode.ICON_SIZE * 2 + 28;
				int num = attentions.length;
				if (num == 1)
					ax = x;
				else if (num == 2) {
					ax = x - (ProcessDrawNode.ICON_SIZE + 28) / 2;
				} else {
					ax = x - ((num + 1) / 2 * ProcessDrawNode.ICON_SIZE + 28);
				}
				for (Record ar : attentions) {
					String employeeName = ar.getAttribute(ACTIVITITY_NAME);
					employeeName = getEmployeeName(employeeName, "");
					ProcessDrawNode attentionNode = generateProcessNode(ax, ay, 4, employeeName);
					attentionNode.setDescription("知会人");
					if (activityStatus == 1) {
						attentionNode.setActiveIcon();
					} else {
						attentionNode.setNormalIcon();
					}
					draw.addDrawItem(attentionNode, true);
					ProcessNodeConnector pnc = generateDrawPath(drawNode, attentionNode);
					pnc.setCanDrag(false);
					draw.addDrawItem(pnc, true);
					ax += ProcessDrawNode.ICON_SIZE + 28;
				}
				hasAttention = true;
			}
			if (direction == 0)
				x += ProcessDrawNode.ICON_SIZE * 2;
			else
				x -= ProcessDrawNode.ICON_SIZE * 2;
			if (x > width) {
				direction = 1;
				x -= ProcessDrawNode.ICON_SIZE * 3;
				if (hasAttention) {
					y += ProcessDrawNode.ICON_SIZE * 4 + 28;
				} else {
					y += ProcessDrawNode.ICON_SIZE * 2 + 28;
				}
				hasAttention = false;
			}
			if (x < 10) {
				direction = 0;
				x += ProcessDrawNode.ICON_SIZE * 3;
				if (hasAttention) {
					y += ProcessDrawNode.ICON_SIZE * 4 + 28;
				} else {
					y += ProcessDrawNode.ICON_SIZE * 2 + 28;
				}
				hasAttention = false;
			}
			count++;
			preDrawNode = drawNode;
		}
		return draw;
	}

	public static String getAppromentComment(int activityType, int activityStatus) {
		switch (activityType) {
		case 0:
			return "申请人";
		case 1:
			return "";
		case 2:
		case 3:
			switch (activityStatus) {
			case 2:
				return "通过";
			case 3:
				return "驳回";
			case 4:
				return "回退给申请人";
			case 5:
				return "回退";
			case 6:
				return "转交";
			case 8:
				return "停滞驳回";
			default:
				return "";
			}
		}
		return "";
	}

	public static String getEmployeeName(String employeeId, String backViewName) {
		if (employeeId == null || "0".equals(employeeId)) {
			employeeId = backViewName;
		} else {
			employeeId = KeyValueManager.getValue("employees", employeeId);
			if (BaseHelpUtils.isNullOrEmpty(employeeId)) {// 如果为空，则将节点的显示名称为该节点定义的角色名称
				employeeId = backViewName;
			}
		}
		return employeeId;
	}

	public static String getEmployeeName(String employeeId) {
		return KeyValueManager.getValue("employees", employeeId);
	}

	public static String generateComment(Record r) {
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormat dtf = DateTimeFormat.getFormat(dateFormat);
		StringBuilder sb = new StringBuilder();
		if (r.getAttribute("instanceActivityStartTime") != null) {
			sb.append("开始时间：");
			sb.append(dtf.format(r.getAttributeAsDate("instanceActivityStartTime")));
			sb.append("<BR>");
		}
		if (r.getAttribute("operateTime") != null) {
			sb.append("处理时间：");
			sb.append(dtf.format(r.getAttributeAsDate("operateTime")));
			sb.append("<BR>");
		}
		Record[] attentions = r.getAttributeAsRecordArray(ATTENTION_NAME);
		Record[] tasks = r.getAttributeAsRecordArray(TASK_NAME);
		int poolType = 0;
		if (r.getAttribute(POOL_TYPE_NAME) != null)
			poolType = r.getAttributeAsInt(POOL_TYPE_NAME);
		if (attentions != null && attentions.length > 0) {
			sb.append("知会人：");
			int count = 0;
			for (Record attention : attentions) {
				String employeeName = getEmployeeName(attention.getAttribute(ACTIVITITY_NAME));
				if (count > 0)
					sb.append(",");
				sb.append(employeeName);
				count++;
			}
			sb.append("<BR>");
		}
		if (tasks != null && tasks.length > 0) {
			if (poolType == 0) {
				sb.append("本流程将由：");
				int count = 0;
				for (Record task : tasks) {
					String employeeName = getEmployeeName(task.getAttribute(ACTIVITITY_NAME));
					if (count > 0)
						sb.append(",");
					sb.append(employeeName);
					count++;
				}
				sb.append(" 任意一人来处理");
			} else {
				sb.append("本流程需要：");
				int count = 0;
				for (Record task : tasks) {
					String employeeName = getEmployeeName(task.getAttribute(ACTIVITITY_NAME));
					if (count > 0)
						sb.append(",");
					sb.append(employeeName);
					count++;
				}
				sb.append(" 都同意才算通过，目前：");
				count = 0;
				List<Record> taskList = Arrays.asList(tasks);
				Collections.sort(taskList, (Record r1, Record r2) -> r1.getAttributeAsInt(STATUS_NAME).compareTo(r2.getAttributeAsInt(STATUS_NAME)));
				for (Record task : taskList) {
					String employeeName = getEmployeeName(task.getAttribute(ACTIVITITY_NAME));
					int activityStatus = task.getAttributeAsInt(STATUS_NAME);
					if (count > 0)
						sb.append(" , ");
					sb.append(employeeName);
					sb.append(" ");
					sb.append(activityStatus == 2 ? "已审批" : "未审批");
					count++;
				}
				sb.append("<BR>");
			}
		}
		if (r.getAttribute("processComment") != null) {
			sb.append("处理意见：");
			sb.append(r.getAttribute("processComment"));
		}
		return sb.toString();
	}

	public static ProcessDrawNode generateProcessNode(int x, int y, int activityType, String activityName) {
		String c = activityName;
		ProcessDrawNode approval1 = new ProcessDrawNode(x, y, ProcessDrawNode.ICON_SIZE, true);
		approval1.setWidth(ProcessDrawNode.ICON_SIZE);
		approval1.setHeight(ProcessDrawNode.ICON_SIZE + 24);
		approval1.setNodeType(getActivityType(activityType));
		switch (activityType) {
		case 0:
			approval1.setContent(c);
			break;
		case 1:
			approval1.setContent("结束");
			break;
		case 2:
			approval1.getDrawImage().setTitle("审批人");
			approval1.setContent(c);
			break;
		case 3:
			approval1.getDrawImage().setTitle("处理人");
			approval1.setContent(c);
			break;
		case 4:
			approval1.getDrawImage().setTitle("知会人");
			approval1.setContent(c);
			break;
		}
		return approval1;
	}

	public static ProcessNodeConnector generateDrawPath(ProcessDrawNode start, ProcessDrawNode end) {
		ProcessNodeConnector path = new ProcessNodeConnector(true);
		path.setStartPoint(start.getNextConnectPoint(end));
		path.setEndPoint(end.getPreviousConnectPoint(start));
		path.setEndArrow(ArrowStyle.BLOCK);
		path.setPreviousNode(start);
		path.setNextNode(end);
		path.setCanDrag(false);
		if (start.getNodeType().equals(ProcessDrawNode.ACTIVITY_ATTENTION)
				|| end.getNodeType().equals(ProcessDrawNode.ACTIVITY_ATTENTION)) {
			path.setLinePattern(LinePattern.DOT);
		}
		start.getNextConnectors().add(path);
		end.getPreviousConnectors().add(path);
		return path;
	}

	public static String getActivityType(int activityType) {
		switch (activityType) {
		case 0:
			return ProcessDrawNode.ACTIVITY_START;
		case 1:
			return ProcessDrawNode.ACTIVITY_END;
		case 2:
			return ProcessDrawNode.ACTIVITY_APPROVAL;
		case 3:
			return ProcessDrawNode.ACTIVITY_PROCESSOR;
		case 4:
			return ProcessDrawNode.ACTIVITY_ATTENTION;
		}
		return null;
	}

}
