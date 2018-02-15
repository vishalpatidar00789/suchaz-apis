import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OccasionImageSuchaz } from './occasion-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OccasionImageSuchaz>;

@Injectable()
export class OccasionImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/occasion-images';

    constructor(private http: HttpClient) { }

    create(occasionImage: OccasionImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(occasionImage);
        return this.http.post<OccasionImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(occasionImage: OccasionImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(occasionImage);
        return this.http.put<OccasionImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OccasionImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OccasionImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<OccasionImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OccasionImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OccasionImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OccasionImageSuchaz[]>): HttpResponse<OccasionImageSuchaz[]> {
        const jsonResponse: OccasionImageSuchaz[] = res.body;
        const body: OccasionImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OccasionImageSuchaz.
     */
    private convertItemFromServer(occasionImage: OccasionImageSuchaz): OccasionImageSuchaz {
        const copy: OccasionImageSuchaz = Object.assign({}, occasionImage);
        return copy;
    }

    /**
     * Convert a OccasionImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(occasionImage: OccasionImageSuchaz): OccasionImageSuchaz {
        const copy: OccasionImageSuchaz = Object.assign({}, occasionImage);
        return copy;
    }
}
