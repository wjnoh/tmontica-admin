import * as React from "react";

export interface IOrderStatusCircleProps {
  statusName: string;
  statusCount: number;
  isActive?: boolean;
  initializeTodayStatus(): void;
  handleClickTodayStatus(statusName: string): void;
}

function OrderStatusCircle(props: IOrderStatusCircleProps) {
  const {
    statusName,
    statusCount,
    isActive,
    handleClickTodayStatus,
    initializeTodayStatus
  } = props;

  return (
    <div
      className={`order-circle border ${isActive ? "btn-success text-white" : "btn-light"}`}
      onClick={() => (isActive ? initializeTodayStatus() : handleClickTodayStatus(statusName))}
    >
      <div className="order-circle-name">{statusName}</div>
      <div>
        <span className="order-cnt">{statusCount}건</span>
      </div>
    </div>
  );
}

export default OrderStatusCircle;