import { Moment } from 'moment';

export interface IDocument {
  id?: number;
  name?: string;
  documentContentType?: string;
  document?: any;
  date?: Moment;
}

export class Document implements IDocument {
  constructor(public id?: number, public name?: string, public documentContentType?: string, public document?: any, public date?: Moment) {}
}
