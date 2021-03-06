import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { VendorImageSuchaz } from './vendor-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<VendorImageSuchaz>;

@Injectable()
export class VendorImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/vendor-images';

    constructor(private http: HttpClient) { }

    create(vendorImage: VendorImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(vendorImage);
        return this.http.post<VendorImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(vendorImage: VendorImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(vendorImage);
        return this.http.put<VendorImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<VendorImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<VendorImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<VendorImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<VendorImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: VendorImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<VendorImageSuchaz[]>): HttpResponse<VendorImageSuchaz[]> {
        const jsonResponse: VendorImageSuchaz[] = res.body;
        const body: VendorImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to VendorImageSuchaz.
     */
    private convertItemFromServer(vendorImage: VendorImageSuchaz): VendorImageSuchaz {
        const copy: VendorImageSuchaz = Object.assign({}, vendorImage);
        return copy;
    }

    /**
     * Convert a VendorImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(vendorImage: VendorImageSuchaz): VendorImageSuchaz {
        const copy: VendorImageSuchaz = Object.assign({}, vendorImage);
        return copy;
    }
}
