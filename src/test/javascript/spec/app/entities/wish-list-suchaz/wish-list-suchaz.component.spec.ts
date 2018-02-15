/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListSuchazComponent } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.component';
import { WishListSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.service';
import { WishListSuchaz } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.model';

describe('Component Tests', () => {

    describe('WishListSuchaz Management Component', () => {
        let comp: WishListSuchazComponent;
        let fixture: ComponentFixture<WishListSuchazComponent>;
        let service: WishListSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListSuchazComponent],
                providers: [
                    WishListSuchazService
                ]
            })
            .overrideTemplate(WishListSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new WishListSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.wishLists[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
