/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemCommonAttributeSuchazComponent } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz.component';
import { ItemCommonAttributeSuchazService } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz.service';
import { ItemCommonAttributeSuchaz } from '../../../../../../main/webapp/app/entities/item-common-attribute-suchaz/item-common-attribute-suchaz.model';

describe('Component Tests', () => {

    describe('ItemCommonAttributeSuchaz Management Component', () => {
        let comp: ItemCommonAttributeSuchazComponent;
        let fixture: ComponentFixture<ItemCommonAttributeSuchazComponent>;
        let service: ItemCommonAttributeSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemCommonAttributeSuchazComponent],
                providers: [
                    ItemCommonAttributeSuchazService
                ]
            })
            .overrideTemplate(ItemCommonAttributeSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemCommonAttributeSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemCommonAttributeSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ItemCommonAttributeSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.itemCommonAttributes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
