/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { GiftWrapperSuchazComponent } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.component';
import { GiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.service';
import { GiftWrapperSuchaz } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.model';

describe('Component Tests', () => {

    describe('GiftWrapperSuchaz Management Component', () => {
        let comp: GiftWrapperSuchazComponent;
        let fixture: ComponentFixture<GiftWrapperSuchazComponent>;
        let service: GiftWrapperSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [GiftWrapperSuchazComponent],
                providers: [
                    GiftWrapperSuchazService
                ]
            })
            .overrideTemplate(GiftWrapperSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftWrapperSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftWrapperSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new GiftWrapperSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.giftWrappers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
