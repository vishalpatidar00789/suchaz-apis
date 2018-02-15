/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListItemSuchazComponent } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.component';
import { WishListItemSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.service';
import { WishListItemSuchaz } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.model';

describe('Component Tests', () => {

    describe('WishListItemSuchaz Management Component', () => {
        let comp: WishListItemSuchazComponent;
        let fixture: ComponentFixture<WishListItemSuchazComponent>;
        let service: WishListItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListItemSuchazComponent],
                providers: [
                    WishListItemSuchazService
                ]
            })
            .overrideTemplate(WishListItemSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListItemSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new WishListItemSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.wishListItems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
