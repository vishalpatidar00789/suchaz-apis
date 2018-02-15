/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemAttributeTypeSuchazComponent } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz.component';
import { ItemAttributeTypeSuchazService } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz.service';
import { ItemAttributeTypeSuchaz } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz.model';

describe('Component Tests', () => {

    describe('ItemAttributeTypeSuchaz Management Component', () => {
        let comp: ItemAttributeTypeSuchazComponent;
        let fixture: ComponentFixture<ItemAttributeTypeSuchazComponent>;
        let service: ItemAttributeTypeSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemAttributeTypeSuchazComponent],
                providers: [
                    ItemAttributeTypeSuchazService
                ]
            })
            .overrideTemplate(ItemAttributeTypeSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemAttributeTypeSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAttributeTypeSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ItemAttributeTypeSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.itemAttributeTypes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
