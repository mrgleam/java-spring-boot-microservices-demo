import { Address } from "./address";
import { Item } from "./item";
export interface Order {
    id: string | null;
    userId: string;
    paymentMode: string;
    lineItems: Item[];
    shippingAddress: Address;
    total: number;
    orderStatus: string | null;
    responseMessage: string | null;
}