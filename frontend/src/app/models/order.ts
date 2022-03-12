import { Item } from "./item";

export interface Order {
    id: string;
    lineItems: Item[];
    total: number;
    orderStatus: string;
    responseMessage: string;
}