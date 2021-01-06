export interface IDocument {
  id?: number;
  name?: string;
  documentContentType?: string;
  document?: any;
}

export class Document implements IDocument {
  constructor(public id?: number, public name?: string, public documentContentType?: string, public document?: any) {}
}
