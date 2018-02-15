/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { OfferSuchazComponent } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz.component';
import { OfferSuchazService } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz.service';
import { OfferSuchaz } from '../../../../../../main/webapp/app/entities/offer-suchaz/offer-suchaz.model';

describe('Component Tests', () => {

    describe('OfferSuchaz Management Component', () => {
        let comp: OfferSuchazComponent;
        let fixture: ComponentFixture<OfferSuchazComponent>;
        let service: OfferSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OfferSuchazComponent],
                providers: [
                    OfferSuchazService
                ]
            })
            .overrideTemplate(OfferSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OfferSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OfferSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new OfferSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.offers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
